# Introduce

各种插件。

# 上传命令

1. 代码写好以后执行`buildPlugin`得到jar文件。
2.
准备认证，认证的jar包路径是[https://github.com/JetBrains/marketplace-zip-signer/releases/tag/0.1.8](https://github.com/JetBrains/marketplace-zip-signer/releases/download/0.1.8/marketplace-zip-signer-cli.jar)。
3. 将jar包移动到`/usr/local/lib/marketplace-zip-signer-cli.jar`下。
4. 配置证书和私钥，将路径配置在全局变量中。配置idea的token到全局变量。
5. 执行`publishPlugin`即可。