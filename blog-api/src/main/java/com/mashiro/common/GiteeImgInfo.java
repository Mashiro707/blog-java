package com.mashiro.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 配置码云图床
 * @Author: Mashiro
 * @Date: Created in 2021/7/22 14:20
 */
public class GiteeImgInfo {

    public static String getNowDate() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(now);
    }

    /**
     * 码云私人令牌
     *
     * @param
     * @return {@link null}
     * @author Mashiro
     * @date 2021/7/22 14:21
     */
    public static final String ACCESS_TOKEN = "78298e123080b409b71ea502755dd30c";
    //个人空间名，需要补充
    public static final String OWNER = "shili707";
    //仓库名，需要补充
    public static final String REPO_NAME = "img";
    /**
     * 文件夹路径
     *
     * @param null
     * @return {@link null}
     * @author Mashiro
     * @date 2021/7/22 14:22
     */
    public static final String PATH = "/images/" + getNowDate() + "/";
    //message消息
    public static final String ADD_MESSAGE = "add img";
    public static final String DEl_MESSAGE = "del img";

    /**
     * 新建文件请求路径
     * <p>
     * owner*   仓库所属空间地址(企业、组织或个人的地址path)
     * repo*    仓库路径
     * path*    文件的路径
     * content* 文件内容, 要用 base64 编码
     * message* 提交信息
     * <p>
     * %s =>仓库所属空间地址(企业、组织或个人的地址path)  (owner)
     * %s => 仓库路径(repo)
     * %s => 文件的路径(path)
     */
    public static String CREATE_REPOS_URL = "https://gitee.com/api/v5/repos/%s/%s/contents/%s";
    /**
     * 获取路径下所有的内容
     * <p>
     * owner*   仓库所属空间地址(企业、组织或个人的地址path)
     * repo*    仓库路径
     * path*    文件的路径
     * content* 文件内容, 要用 base64 编码
     * message* 提交信息
     * <p>
     * %s =>仓库所属空间地址(企业、组织或个人的地址path)  (owner)
     * %s => 仓库路径(repo)
     * %s => 文件的路径(path)
     */
    public static String GET_IMG_URL = "https://gitee.com/api/v5/repos/%s/%s/contents/%s";

    /**
     * 删除文件请求路径
     * <p>
     * owner*   仓库所属空间地址(企业、组织或个人的地址path)
     * repo*    仓库路径
     * path*    文件的路径
     * content* 文件内容, 要用 base64 编码
     * message* 提交信息
     * <p>
     * %s =>仓库所属空间地址(企业、组织或个人的地址path)  (owner)
     * %s => 仓库路径(repo)
     * %s => 文件的路径(path)
     */
    public static String DEL_IMG_URL = "https://gitee.com/api/v5/repos/%s/%s/contents/%s";

}
