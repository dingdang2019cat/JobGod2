package com.ant.jobgod.jobgod.config;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public class API {
    public static class URL {
        private static final String BaseUrl = "http://103.238.226.48:81/index.php/";

        //Account
        public static final String IsRegistered = BaseUrl + "home/user/checkTel";
        public static final String Register = BaseUrl + "home/user/register";
        public static final String Login = BaseUrl + "Home/User/login";
        public static final String ModifyPassword = BaseUrl + "Home/user/modPass";

        //Person
        public static final String GetPersonBrief = BaseUrl + "home/user/getPersonBrief";
        public static final String SyncPersonBriefs = BaseUrl + "home/user/syncPeopleBrief";
        public static final String ModifyName = BaseUrl + "Home/index/modName";
        public static final String ModifySign = BaseUrl + "Home/index/modSign";
        public static final String ModifyFace = BaseUrl + "Home/index/modFace";
        public static final String GetUserData = BaseUrl + "home/user/refreshMyData";
        public static final String GetUserDetail = BaseUrl + "home/user/getUserDetail";
        public static final String UpdateUserDetail = BaseUrl + "home/user/updateDetail";

        public static final String GetAttentionFromMe = BaseUrl + "home/Index/getAttFromMe";
        public static final String GetAttentionToMe = BaseUrl + "home/Index/getAttToMe";

        public static final String GetAroundFriends = BaseUrl + "home/user/getNearbyUser";

        public static final String GetCollections = BaseUrl + "home/user/collectList";
        public static final String GetJoin = BaseUrl + "home/user/applyList";

        public static final String Attention = BaseUrl + "home/index/focusUser";
        public static final String UnAttention = BaseUrl + "home/index/unFocusUser";

        public static final String Apply = BaseUrl + "home/user/apply";

        //manager
        public static final String GetContract = BaseUrl + "Home/index/getContract";
        public static final String CancelApply = BaseUrl + "home/user/cancelApply";
        public static final String JodgeBiz = BaseUrl + "home/index/judge";


        //LBS
        public static final String SyncLocation = BaseUrl + "home/index/syncAddress";

        //Public
        public static final String GetBanner = BaseUrl + "home/index/getBanner";
        public static final String GetTopicJobList = BaseUrl + "home/job/getTopicJobList";
        public static final String GetTopicList = BaseUrl + "home/job/getTopicList";
        public static final String GetRecommendList = BaseUrl + "home/job/getHotJobList";
        public static final String GetJobList = BaseUrl + "home/job/getJobList";
        public static final String GetTrades = BaseUrl + "home/index/getTrades";
        public static final String GetJobDetail = BaseUrl + "home/job/jobDetail";
        public static final String Collect = BaseUrl + "home/user/collect";
        public static final String UnCollect = BaseUrl + "home/user/unCollect";

        public static final String GetComment = BaseUrl + "home/job/commentList";
        public static final String Comment = BaseUrl + "home/user/comment";
    }

    public static class KEY {
        public static final String STATUS = "status";
        public static final String INFO = "info";
        public static final String DATA = "data";
    }

    public static class CODE {
        public static final int SUCCEED = 200;
        public static final int Failure = 0;
        public static final int PERMISSION_DENIED = 400;
    }
}
