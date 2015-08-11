package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.RemoteFileModel;
import com.ant.jobgod.jobgod.model.SocietyModel;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.PortraitUploadResponse;

import java.io.File;

/**
 * Created by alien on 2015/7/11.
 */
public class ModifyFacePresenter extends BasePresenter<ModifyFaceActivity> {
    private ImageProvider mProvider;
    private Uri mFace;
    private OnImageSelectListener listener = new OnImageSelectListener() {
        @Override
        public void onImageSelect() {
            getView().showProgress("图片加载中");
        }

        @Override
        public void onImageLoaded(Uri uri) {
            getView().dismissProgress();
            mProvider.corpImage(uri, 300, 300, new OnImageSelectListener() {
                @Override
                public void onImageSelect() {

                }

                @Override
                public void onImageLoaded(Uri uri) {
                    mFace = uri;
                    getView().setImgFace(uri);
                }

                @Override
                public void onError() {
                    Utils.Log("图片加载错误");
                }
            });
        }

        @Override
        public void onError() {
            getView().dismissProgress();
            Utils.Log("图片加载错误");
        }
    };

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
        mProvider.getImageFromCamera(listener);
    }

    public void getImageFromAlbum(){
        mProvider.getImageFromAlbum(listener);
    }

    public void getImageFromNet(){
        mProvider.getImageFromNet(listener);
    }


    public void upload(){
        getView().showProgress("上传中");

        RemoteFileModel.getInstance().putImage(new File(mFace.getPath()), new RemoteFileModel.UploadImageListener() {
            @Override
            public void onComplete(RemoteFileModel.SizeImage path) {
                UserModel.getInstance().modifyFace(path.getSmallImage(), path.getLargeImage(), new StatusCallback() {
                    @Override
                    public void success(String info) {
                        Utils.Toast("上传成功");
                        AccountModel.getInstance().updateAccountData();
                            SocietyModel.getInstance().updateBBSFace(BitmapFactory.decodeFile(mFace.getPath()), new Listeners.SimpleFetchListener<PortraitUploadResponse>() {
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
    }
}
