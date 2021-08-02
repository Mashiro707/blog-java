package com.mashiro.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mashiro.common.GiteeImgInfo;
import com.mashiro.common.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/7/26 22:36
 */
public class UploadImg {

    public static Result uploadImg(MultipartFile multipartFile) throws Exception {
        String originaFileName = multipartFile.getOriginalFilename();
        String suffix = originaFileName.substring(originaFileName.lastIndexOf("."));
        String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + suffix;
        String paramImgFile = Base64.encode(multipartFile.getBytes());
        //设置转存到Gitee仓库参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", GiteeImgInfo.ACCESS_TOKEN);
        paramMap.put("message", GiteeImgInfo.ADD_MESSAGE);
        paramMap.put("content", paramImgFile);
        //转存文件路径
        String targetDir = GiteeImgInfo.PATH + fileName;
        //设置请求路径
        String requestUrl = String.format(GiteeImgInfo.CREATE_REPOS_URL, GiteeImgInfo.OWNER,
                GiteeImgInfo.REPO_NAME, targetDir);

        String resultJson = HttpUtil.post(requestUrl, paramMap);
        JSONObject jsonObject = JSONUtil.parseObj(resultJson);
        //表示操作失败
        if (jsonObject == null || jsonObject.getObj("commit") == null) {
            return Result.error("上传图片失败");
        }
        JSONObject content = JSONUtil.parseObj(jsonObject.getObj("content"));
        return Result.success(content.getObj("download_url"));
    }

}
