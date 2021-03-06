module.exports = {
  outputDir: "blog", // 输出文件目录
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    },
    disableHostCheck: true
  }
};
