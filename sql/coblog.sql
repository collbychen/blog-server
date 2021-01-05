/*
 Navicat Premium Data Transfer
 Date: 27/12/2020 01:49:43
*/
USE coblog;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章详情页链接',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文章标题',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章描述',
  `image_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章的预览图片',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章内容',
  `content_md` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT ' Markdown格式的文章内容',
  `category_id` bigint(0) NULL DEFAULT NULL COMMENT '分类ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '发表时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `is_top` tinyint(0) NULL DEFAULT 0 COMMENT '文章是否置顶  0：否  1：是',
  `is_comment` tinyint(0) NOT NULL DEFAULT 1 COMMENT '是否开启评论 0：关闭 1：开启',
  `is_original` tinyint(0) NOT NULL DEFAULT 1 COMMENT '是否为原创文章 0：转载 1：原创',
  `source_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原文链接，转载文章才需填写',
  `visits` int(0) NOT NULL DEFAULT 0 COMMENT '访问量',
  `status` tinyint(0) NOT NULL COMMENT '状态 0：草稿 1：已发布 2：回收站',
  `type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '文章类型 0：普通文章 1：自定义文章',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_url`(`url`) USING BTREE COMMENT '文章链接唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 'hello-world', 'hello world!', '你好，世界', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '<h1><a id=\"Hello_World_0\"></a>Hello World</h1>\n<p>你好，世界。<br />\n欢迎使用本博客系统。<br />\n写博客需要坚持，一起努力加油吧！</p>\n', '# Hello World\n你好，世界。\n欢迎使用本博客系统。\n写博客需要坚持，一起努力加油吧！', 1, '2019-09-06 14:42:23', '2020-09-22 22:56:19', 0, 1, 1, NULL, 83, 1, 0);
INSERT INTO `article` VALUES (2, 'about', '关于我', '关于我', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '<p style=\"text-align: center;\">\n	<span style=\"font-size: 14pt;\">欢迎来到<strong>我的个人博客</strong></span>\n</p>\n<div class=\"alert alert-danger\">我是一个好人。<br>我真的是一个好人哦！\n</div>\n<div class=\"alert alert-success\">这是个自定义文章示例界面<br>内容完全自定义<br>嘿嘿嘿~</div>\n<div class=\"alert alert-danger\">博客项目在<a href=\"https://github.com/iszhouhua/blog\">这里https://github.com/iszhouhua/blog</a><br>参考的博客主题在<a href=\"https://github.com/ZEROKISEKI/hexo-theme-gal\">这里https://github.com/ZEROKISEKI/hexo-theme-gal</a><br>参考网站在<a href=\"https://www.mmgal.com/\">这里https://www.mmgal.com/</a></div>', '<p style=\"text-align: center;\">\r\n	<span style=\"font-size: 14pt;\">欢迎来到<strong>我的个人博客</strong></span>\r\n</p>\r\n<div class=\"alert alert-danger\">我是一个好人。<br>我真的是一个好人哦！\r\n</div>\r\n<div class=\"alert alert-success\">这是个自定义文章示例界面<br>内容完全自定义<br>嘿嘿嘿~</div>\r\n<div class=\"alert alert-danger\">博客项目在<a href=\"https://github.com/iszhouhua/blog\">这里https://github.com/iszhouhua/blog</a><br>参考的博客主题在<a href=\"https://github.com/ZEROKISEKI/hexo-theme-gal\">这里https://github.com/ZEROKISEKI/hexo-theme-gal</a><br>参考网站在<a href=\"https://www.mmgal.com/\">这里https://www.mmgal.com/</a></div>', 2, '2020-09-08 14:42:23', '2020-09-22 22:58:03', 0, 1, 1, NULL, 55, 1, 0);
INSERT INTO `article` VALUES (7, '啊啊啊啊啊啊啊啊啊啊啊', '啊啊啊啊啊啊啊啊啊啊啊', 'adsdasda', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '<p>啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊</p>\n', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', 1, '2020-09-16 18:16:34', '2020-10-03 00:58:20', 0, 1, 1, NULL, 134, 1, 0);
INSERT INTO `article` VALUES (9, '111111111111', '111111111111', '111111111111111111', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '<p>1111111111111111</p>', '1111111111111111', 2, '2020-09-17 22:47:00', '2020-09-17 23:02:25', 0, 1, 1, NULL, 268, 1, 0);
INSERT INTO `article` VALUES (10, '测试用例', '测试用例', '测试用例', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '<p>啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥v</p>', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥v', 1, '2020-10-01 19:45:47', '2020-10-01 19:45:47', 0, 1, 1, NULL, 12, 1, 0);
INSERT INTO `article` VALUES (11, '测试用例2', '测试用例2', '11111111111111111', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '<p>23123111111111111111111</p>', '23123111111111111111111', 1, '2020-10-01 19:46:11', '2020-10-01 19:46:11', 0, 1, 1, NULL, 10, 1, 0);
INSERT INTO `article` VALUES (12, '测试用例33', '测试用例33', '222222222222', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '<p>223123131232131</p>', '223123131232131', 1, '2020-10-01 19:46:27', '2020-10-01 19:46:27', 0, 1, 1, NULL, 15, 1, 0);
INSERT INTO `article` VALUES (13, '测试用例4', '测试用例4', '萨达萨达as', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '<p>很多情况下，我们在执行点击按钮跳转页面之前还会执行一系列方法，这时可以使用 this.$router.push(location) 来修改 url，完成跳转。</p>\n<p>push 后面可以是对象，也可以是字符串：</p>\n<p>// 字符串<br /> this.router.push(\'/home/first\') // 对象 this.router.push({ path: &lsquo;/home/first&rsquo; })<br /> // 命名的路由<br /> this.$router.push({ name: &lsquo;home&rsquo;, params: { userId: wise }})<br /> 跳转页面并传递参数的方法：</p>\n<p>1.Params</p>\n<p>由于动态路由也是传递params的，所以在 this.$router.push() 方法中path不能和params一起使用，否则params将无效。需要用name来指定页面。</p>\n<p>及通过路由配置的name属性访问</p>', '很多情况下，我们在执行点击按钮跳转页面之前还会执行一系列方法，这时可以使用 this.$router.push(location) 来修改 url，完成跳转。\n\npush 后面可以是对象，也可以是字符串：\n\n// 字符串\nthis.$router.push(\'/home/first\')\n// 对象\nthis.$router.push({ path: \'/home/first\' })\n// 命名的路由\nthis.$router.push({ name: \'home\', params: { userId: wise }})\n跳转页面并传递参数的方法：\n\n1.Params\n\n由于动态路由也是传递params的，所以在 this.$router.push() 方法中path不能和params一起使用，否则params将无效。需要用name来指定页面。\n\n及通过路由配置的name属性访问', 1, '2020-10-01 19:46:51', '2020-10-16 09:24:16', 0, 1, 1, NULL, 23, 1, 0);
INSERT INTO `article` VALUES (14, '一个伪装成文本编辑器的浏览器', '一个伪装成文本编辑器的浏览器', '一个伪装成文本编辑器的浏览器', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '<p>Typora可以根据当前文档的标题层级，自动生成并显示大纲，窗口的右下角并有字数显示。</p>\n<h2><a id=\"_2\"></a>标题的使用</h2>\n<h3><a id=\"_4\"></a>标题的使用格式</h3>\n<p># 一阶标题 或者快捷键Ctrl+1</p>\n<p>##二阶标题 或者快捷键Ctrl+2</p>\n<p>###三阶标题 或者快捷键Ctrl+3</p>\n<p>####四阶标题 或者快捷键Ctrl+4</p>\n<p>#####五阶标题 或者快捷键Ctrl+5</p>\n<p>######六阶标题 或者快捷键Ctrl+6</p>\n<h3><a id=\"Typora_18\"></a>标题Typora显示形式是</h3>\n<p><img src=\"https://img-blog.csdn.net/20180108112546034?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvU0lNQkExOTQ5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<h2><a id=\"_22\"></a>文本居中</h2>\n<h3><a id=\"_24\"></a>文本居中使用格式</h3>\n<pre><code class=\"lang-\">\\&lt;center\\&gt;这是要居中的文本内容\\&lt;/center\\&gt;1\n</code></pre>\n<h3><a id=\"Typora_30\"></a>文本居中在Typora中显示形式是</h3>\n<p>这是要居中的文本</p>\n<p>注：Typora目前并不会直接预览居中效果——相应的效果只有输出文本的时候才会显现。</p>\n<h2><a id=\"_40\"></a>下划线</h2>\n<h3><a id=\"_42\"></a>下划线使用格式</h3>\n<p>\\下划线的内容&lt;\\u&gt; 或者快捷键Ctrl+U</p>\n<h3><a id=\"Typora_46\"></a>下划线在Typora显示形式是</h3>\n', 'Typora可以根据当前文档的标题层级，自动生成并显示大纲，窗口的右下角并有字数显示。\n\n## 标题的使用\n\n### 标题的使用格式\n\n\\# 一阶标题 或者快捷键Ctrl+1\n\n\\##二阶标题 或者快捷键Ctrl+2\n\n\\###三阶标题 或者快捷键Ctrl+3\n\n\\####四阶标题 或者快捷键Ctrl+4\n\n\\#####五阶标题 或者快捷键Ctrl+5\n\n\\######六阶标题 或者快捷键Ctrl+6\n\n### 标题Typora显示形式是\n\n![这里写图片描述](https://img-blog.csdn.net/20180108112546034?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvU0lNQkExOTQ5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n## 文本居中\n\n### 文本居中使用格式\n\n```\n\\<center\\>这是要居中的文本内容\\</center\\>1\n```\n\n### 文本居中在Typora中显示形式是\n\n\n\n这是要居中的文本\n\n\n\n注：Typora目前并不会直接预览居中效果——相应的效果只有输出文本的时候才会显现。\n\n## 下划线\n\n### 下划线使用格式\n\n\\下划线的内容\\<\\u> 或者快捷键Ctrl+U\n\n### 下划线在Typora显示形式是', 1, '2020-10-16 09:23:17', '2020-10-16 09:23:17', 0, 1, 1, NULL, 26, 1, 0);

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(0) NOT NULL COMMENT '文章ID',
  `tag_id` bigint(0) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章与标签的对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO `article_tag` VALUES (3, 9, 2);
INSERT INTO `article_tag` VALUES (5, 2, 2);
INSERT INTO `article_tag` VALUES (6, 1, 1);
INSERT INTO `article_tag` VALUES (7, 10, 2);
INSERT INTO `article_tag` VALUES (8, 11, 2);
INSERT INTO `article_tag` VALUES (9, 12, 2);
INSERT INTO `article_tag` VALUES (15, 7, 4);
INSERT INTO `article_tag` VALUES (16, 14, 1);
INSERT INTO `article_tag` VALUES (17, 13, 2);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cateName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '技术', 'tech');
INSERT INTO `category` VALUES (2, '壁纸', 'wallpaper');
INSERT INTO `category` VALUES (3, '美食', 'food');
INSERT INTO `category` VALUES (4, '音乐', 'music');
INSERT INTO `category` VALUES (5, '游戏', 'game');
INSERT INTO `category` VALUES (6, '电影', 'film');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论者使用的浏览器',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论者的ip地址',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父级评论',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` bigint(0) NOT NULL COMMENT '评论者Id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论人名称',
  `reply_id` bigint(0) NULL DEFAULT NULL COMMENT '回复的人',
  `replier` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复的人名称',
  `target_type` int(0) NOT NULL COMMENT '目标类型 1：文章 2：评论',
  `article_id` bigint(0) NOT NULL COMMENT '评论的文章',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '评论时间',
  `status` tinyint(0) NOT NULL COMMENT '评论状态 0：待审核 1：已发布 2：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '参数名',
  `value` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数值',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '参数类型 1：全局变量 2：系统配置',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE COMMENT '参数名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 'BLOG_TITLE', '我的个人博客', 1, '网站标题');
INSERT INTO `config` VALUES (2, 'BLOG_KEYWORDS', '个人博客,博客', 1, '网站关键词，关键字之间用,分隔');
INSERT INTO `config` VALUES (3, 'BLOG_DESCRIPTION', '这是我的个人博客', 1, '网站描述');
INSERT INTO `config` VALUES (4, 'BLOG_URL', 'http://127.0.0.1:8080/', 1, '网站链接');
INSERT INTO `config` VALUES (5, 'BLOG_AUTHOR', '悠然小宋', 1, '网站作者');
INSERT INTO `config` VALUES (6, 'BLOG_AVATAR', '/images/avatar.jpg', 1, '头像');
INSERT INTO `config` VALUES (7, 'BLOG_NOTICE', '这是公告', 1, '公告');
INSERT INTO `config` VALUES (8, 'DEFAULT_IMAGE', '/images/default-preview.jpg', 1, '默认预览图');
INSERT INTO `config` VALUES (9, 'AUTHOR_DESCRIPTION', '你凭什么认为你一努力就能超过别人，这样岂不是对那些一直努力的人来说很不公平。', 1, '头像下的描述内容');
INSERT INTO `config` VALUES (10, 'FILING_ICP', NULL, 1, 'ICP备案');
INSERT INTO `config` VALUES (11, 'FILING_SECURITY', NULL, 1, '公安备案');
INSERT INTO `config` VALUES (12, 'COMMENT_CHECK', 'false', 2, '评论是否需要校检');
INSERT INTO `config` VALUES (13, 'BACKGROUND_LIST', '[\"/images/slide/background1.jpg\",\"/images/slide/background2.jpg\",\"/images/slide/background3.jpg\",\"/images/slide/background4.jpg\",\"/images/slide/background5.jpg\",\"/images/slide/background6.jpg\"]', 1, '网站的背景图片集合，格式为JSON数组');
INSERT INTO `config` VALUES (14, 'BLOG_HEAD', '<meta name=\"google-site-verification\" content=\"_-xMXp-rAz3pUIziyjcIoJ8D9VOV6yb7XrB5qaeq_Fg\" />\n<meta name=\"baidu-site-verification\" content=\"HsvFoNGZDC\" />\n<meta name=\"360-site-verification\" content=\"42179cf63604add68d1e11b26f44c322\" />\n<meta name=\"sogou_site_verification\" content=\"XvRe3wQaqS\"/>\n', 1, '博客头部插入的代码，如站点验证代码等');
INSERT INTO `config` VALUES (15, 'BLOG_SCRIPT', '<script>/*百度统计*/     var _hmt = _hmt || [];    (function() {        var hm = document.createElement(\"script\");        hm.src = \"https://hm.baidu.com/hm.js?77737a53e73e57c6c44b4640d1c108a1\";        var s = document.getElementsByTagName(\"script\")[0];        s.parentNode.insertBefore(hm, s);    })();</script>\n<script>/*百度自动推送*/  (function(){     var bp = document.createElement(\'script\');     var curProtocol = window.location.protocol.split(\':\')[0];     if (curProtocol === \'https\') {         bp.src = \'https://zz.bdstatic.com/linksubmit/push.js\';     }     else {         bp.src = \'http://push.zhanzhang.baidu.com/push.js\';     }     var s = document.getElementsByTagName(\"script\")[0];     s.parentNode.insertBefore(bp, s); })(); </script>\n<script>/*360自动收录*/ (function(){ var src = (document.location.protocol == \"http:\") ? \"http://js.passport.qihucdn.com/11.0.1.js?6396cb521d4daf069727dc8d995a3878\":\"https://jspassport.ssl.qhimg.com/11.0.1.js?6396cb521d4daf069727dc8d995a3878\"; document.write(\'<script src=\"\' + src + \'\" id=\"sozz\"><\\/script>\'); })(); </script>\n', 1, '博客尾部插入的脚本，如统计代码、推送代码等');
INSERT INTO `config` VALUES (16, 'FILE_STORAGE', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"WfU9icaIyk9W550rpBiMqgimO-uN6JXXQw17z9S_\",\"qiniuBucketName\":\"collby\",\"qiniuDomain\":\"https://cdn.coblog.cn\",\"qiniuPrefix\":\"server/\",\"qiniuSecretKey\":\"QJ2QMSa0JONiu4bX5rbbjaqDSwvxL1g9IhAa-ESM\",\"localDirectory\":\"\",\"localDomain\":\"\",\"type\":1,\"localPrefix\":\"upload\"}', 2, '云/本地存储配置信息');

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接名称',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '链接类型 1：友情链接 2：个人链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '链接表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES (1, '小宋个人博客', 'http://www.coblog.com', 2);
INSERT INTO `link` VALUES (2, 'GitHub', 'https://github.com/collbychen', 2);
INSERT INTO `link` VALUES (3, '玉捷博客', 'https://lsyblog.com/', 1);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'IP地址',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问链接',
  `referer` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源',
  `user_agent` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `duration` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '接口响应时长（单位：毫秒）',
  `type` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问类型',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '访问参数',
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '返回结果',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行的方法',
  `is_normal` tinyint(0) NOT NULL DEFAULT 1 COMMENT '请求是否正常 1：正常 0：异常',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `operating_system` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, '192.168.113.1', '内网IP', 'http://localhost:8080/', NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36', '2020-09-08 14:43:15', '178', 'GET', '{}', '\"index\"', 'com.iszhouhua.blog.controller.front.IndexController.index', 1, 'Chrome 8', 'Windows 10');
INSERT INTO `log` VALUES (2, '192.168.113.1', '内网IP', 'http://localhost:8080/category/', 'http://localhost:8080/', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36', '2020-09-08 14:43:21', '22', 'GET', '{}', '\"categories\"', 'com.iszhouhua.blog.controller.front.CategoryController.tags', 1, 'Chrome 8', 'Windows 10');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单链接',
  `is_blank` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否在新窗口打开菜单  0：否，1：是',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Font Awesome图标',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '菜单排序，越小的越靠前',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_url`(`url`) USING BTREE COMMENT '菜单链接唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '首页', '/', 0, 'fa-home', 1);
INSERT INTO `menu` VALUES (2, '分类', '/category/', 0, 'fa-list', 3);
INSERT INTO `menu` VALUES (3, '标签', '/tag/', 0, 'fa-tags', 4);
INSERT INTO `menu` VALUES (4, '关于我', '/about.html', 0, 'fa-user', 5);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, '超级管理员');
INSERT INTO `roles` VALUES (2, '普通用户');
INSERT INTO `roles` VALUES (3, '测试角色1');
INSERT INTO `roles` VALUES (4, '测试角色2');
INSERT INTO `roles` VALUES (5, '测试角色3');

-- ----------------------------
-- Table structure for spider
-- ----------------------------
DROP TABLE IF EXISTS `spider`;
CREATE TABLE `spider`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爬虫标识名',
  `title_rule` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题爬取规则',
  `content_rule` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容爬取规则',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '爬虫规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of spider
-- ----------------------------
INSERT INTO `spider` VALUES (1, '博客园', 'h1.postTitle', 'div.postBody');
INSERT INTO `spider` VALUES (2, 'CSDN', 'h1.title-article', 'div#content_views');
INSERT INTO `spider` VALUES (3, '简书', 'h1.title', 'div.show-content');
INSERT INTO `spider` VALUES (4, '思否', '#articleTitle a', 'div.article');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签链接',
  `color` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签颜色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_url`(`url`) USING BTREE COMMENT '标签链接唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '自然', 'live', '#00FA9A');
INSERT INTO `tag` VALUES (2, '动漫', 'acg', '#EE82EE');
INSERT INTO `tag` VALUES (3, '搞笑', 'funny', '#FFFF00');
INSERT INTO `tag` VALUES (4, '电脑', 'cmp', '	#00BFFF');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `login_fail_num` tinyint(0) NOT NULL DEFAULT 0 COMMENT '登录失败次数，超过一定次数禁止登录',
  `is_disable` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否禁用 0：否 1：是',
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `is_admin` bit(1) NOT NULL DEFAULT b'0' COMMENT '用户是否为管理员 0：不是 1：是',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '悠然小宋', '$2a$10$vk/CY7Xp2FE.AccSWaVdg.5F3B57gbxdrmeI3NqM69dk5S3.Xva/i', '376010115@qq.com', '2020-12-17 13:16:43', '2020-09-08 14:42:24', 0, 0, '/images/avatar.jpg', b'1');

SET FOREIGN_KEY_CHECKS = 1;
