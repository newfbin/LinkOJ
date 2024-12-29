package com.newfbin.codesandbox.docker;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;

public class DockerHttpDemo {

    public static void main(String[] args) {
        connectDocker();
    }

    /**
     * 连接docker服务器
     *
     * @return
     */
    public static DockerClient connectDocker() {
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp:/192.168.13.129:2375").build();
        Info info = dockerClient.infoCmd().exec();
        String infoStr = JSONObject.toJSONString(info);
        System.out.println("docker的环境信息如下：=================");
        System.out.println(infoStr);
        return dockerClient;
    }

    //public static DockerClient getDockerClient() {
    //    // 进行安全认证
    //    DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
    //            // 服务器ip
    //            .withDockerHost("tcp://192.168.13.129:2375")
    //            .withDockerTlsVerify(true)
    //            // 压缩包解压的路径
    //            .withDock erCertPath("D:\\学习文件\\自学\\项目\\项目学习-鱼皮\\07-OJ判题系统\\代码\\LinkOJ\\code-sandbox\\src\\main\\resources\\tls-client-certs-docker")
    //            // API版本 可通过在服务器 docker version 命令查看
    //            .withApiVersion("1.43")
    //            // 默认
    //            .withRegistryUrl("https://index.docker.io/v1/")
    //            // 默认
    //            .withRegistryUsername("docker")
    //            // 默认
    //            .withRegistryPassword("123456")
    //            // 默认
    //            .withRegistryEmail("an23gn@163.com")
    //            .build();
    //    // docker命令执行工厂
    //    DockerCmdExecFactory dockerCmdExecFactory = new JerseyDockerCmdExecFactory()
    //            .withReadTimeout(60000)
    //            .withConnectTimeout(60000)
    //            .withMaxTotalConnections(100)
    //            .withMaxPerRouteConnections(10);
    //    DockerClient dockerClient = DockerClientBuilder.getInstance(config).withDockerCmdExecFactory(dockerCmdExecFactory).build();
    //    return dockerClient;
    //}
}
