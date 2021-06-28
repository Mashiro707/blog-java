export default {
  searchFlag: false,
  loginFlag: false,
  registerFlag: false,
  forgetFlag: false,
  emailFlag: false,
  drawer: false,
  loginUrl: "",
  userId: null,
  avatar: null,
  nickname: null,
  intro: null,
  loginType: null,
  email: null,
  blogInfo: {},
  siteInfo: "",
  introduction: {
    avatar: "",
    name: "",
    rollText: []
  },
  commentQuery: {
    //用于后端判断该评论所在页面类型(文章、关于我)
    page: 0,
    blogId: null,
    pageNum: 1,
    pageSize: 5
  },
  commentCount: 0,
  commentTotalPage: 0,
  comments: [],
  parentCommentId: -1,
  commentForm: {
    content: "",
    nickname: "",
    email: "",
    website: "",
    notice: true
  },
  //博客文章渲染完成的标记
  isBlogRenderComplete: false,
  //文章页面路由到首页的标记
  isBlogToHome: false,
  //可视窗口大小
  clientSize: {
    clientHeight: 0,
    clientWidth: 1080
  },
  webTitleSuffix: ""
};
