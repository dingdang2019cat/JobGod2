package com.ant.jobgod.jobgod.module.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.ant.jobgod.imagetool.corpimage.CropImageIntentBuilder;
import com.ant.jobgod.imagetool.imageprovider.ImageElement;
import com.ant.jobgod.imagetool.imageprovider.ImageProvider;
import com.ant.jobgod.imagetool.imageprovider.OnImageSelectListener;
import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.RemoteFileModel;
import com.ant.jobgod.jobgod.model.SocietyModel;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.PortraitUploadResponse;

import java.io.File;

/**
 * Created by alien on 2015/7/11.
 */
public class ModifyFacePresenter extends BasePresenter<ModifyFaceActivity> {
    private static final int REQUEST_CROP_PICTURE = 1365;

    private static final String TEMP_IMG = "userFaceTempImage.jpg";
    //最终文件名用变量即时变化，为了处理fresco的图片缓存
    private String mFinalImg;
    private ImageProvider mProvider;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        mProvider = new ImageProvider(getView());
    }

    @Override
    protected void onCreateView(ModifyFaceActivity view) {
        super.onCreateView(view);
        Utils.Log(AccountModel.getInstance().getAccount().getFace());
        getView().setImgFace(Uri.parse(AccountModel.getInstance().getAccount().getFace()));
    }

    public void getImageFromCamera(){
        mProvider.getImageFromCamera(new OnImageSelectListener<ImageElement>() {
            @Override
            public void onImageSelect(ImageElement imageElement) {
                startCrop(imageElement.getLargeImage());
            }
        });
    }

    public void getImageFromAlbum(){
        mProvider.getImageFromAlbum(new OnImageSelectListener<ImageElement>() {
            @Override
            public void onImageSelect(ImageElement imageElement) {
                startCrop(imageElement.getLargeImage());
            }
        });
    }

    public void getImageFromNet(){
        mProvider.getImageFromNet(ImageProvider.Searcher.SOSO, new OnImageSelectListener<ImageElement>() {
            @Override
            public void onImageSelect(final ImageElement imageElement) {
                getView().showProgress("图片下载中");
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                DataSource<CloseableReference<CloseableImage>>
                        dataSource = imagePipeline.fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(imageElement.getLargeImage()).build(), this);
                DataSubscriber dataSubscriber = new BaseBitmapDataSubscriber() {
                    @Override
                    protected void onNewResultImpl(Bitmap bitmap) {
                        File temp = FileManager.getInstance().getChild(FileManager.Dir.Image, TEMP_IMG);
                        Utils.BitmapSave(bitmap, temp.getPath());
                        getView().dismissProgress();
                        startCrop(Uri.fromFile(temp));
                    }

                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> closeableReferenceDataSource) {
                        getView().dismissProgress();
                        Utils.Toast("图片下载失败");
                    }
                };
                final Handler handler = new Handler();
                dataSource.subscribe(dataSubscriber, command -> handler.post(command));
            }
        });
    }

    public void startCrop(Uri data){
        //删除上一次的图片
        if (mFinalImg != null)
        FileManager.getInstance().deletChild(FileManager.Dir.Image,mFinalImg);
        //用时间来取名，临时措施
        mFinalImg = System.currentTimeMillis()+".jpg";
        CropImageIntentBuilder cropImage = new CropImageIntentBuilder(960, 1080,960, 1080, Uri.fromFile(FileManager.getInstance().getChild(FileManager.Dir.Image, mFinalImg)));
        cropImage.setSourceImage(data);
        getView().startActivityForResult(cropImage.getIntent(getView()), REQUEST_CROP_PICTURE);
    }

    public void upload(){
        getView().showProgress("上传中");
        RemoteFileModel.getInstance().putImage(FileManager.getInstance().getChild(FileManager.Dir.Image, mFinalImg), new RemoteFileModel.UploadImageListener() {
            @Override
            public void onComplete(RemoteFileModel.SizeImage path) {
                UserModel.getInstance().modifyFace(path.getSmallImage(), path.getLargeImage(), new StatusCallback() {
                    @Override
                    public void success(String info) {
                        Utils.Toast("上传成功");
                        AccountModel.getInstance().updateAccountData();
                        String filepath=FileManager.getInstance().getChild(FileManager.Dir.Image, mFinalImg).getAbsolutePath();
                        if (SocietyModel.getInstance().checkLogin(getView()))
                        SocietyModel.getInstance().updataBBSFace(BitmapFactory.decodeFile(filepath), new Listeners.SimpleFetchListener<PortraitUploadResponse>() {
                            @Override
                            public void onComplete(PortraitUploadResponse portraitUploadResponse) {
                                getView().finish();
                            }
                        });
                    }

                    @Override
                    public void result(int status, String info) {
                        getView().dismissProgress();
                    }
                });
            }

            @Override
            public void onError() {
                Utils.Toast("上传失败");
                getView().dismissProgress();
            }
        });
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        mProvider.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CROP_PICTURE && resultCode == Activity.RESULT_OK){
            getView().setImgFace(Uri.fromFile(FileManager.getInstance().getChild(FileManager.Dir.Image, mFinalImg)));
            //裁剪成功，删除临时文件
            FileManager.getInstance().deletChild(FileManager.Dir.Image, TEMP_IMG);
        }
    }
}
