# SecIoT
- IoT安全漏洞检测平台-服务器平台

## 主要功能
### 一、系统安全
#### 1、系统固件静态分析
- 分析Squashfs和JFFS2文件系统的固件
- 分析固件中第三方库的版本以及此版本存在的CVE漏洞，目前支持以下第三方库的检测：
    - OpenSSL
    - gzlib
    - busybox
    - miniUPnP
    - uclibc
    - OpenSSH
    - Dropbear
    - pcre
    - OpenLDAP
- 分析固件中存在的平台配置风险，目前支持的功能：
    - 分析Linux固件中的用户情况
    - 分析Linux系统是否可通过Dropbear进行SSH远程登录
    - 分析Linux系统是否在Dropbear中配置了公钥
    - 分析Linux系统的计划任务（crontab）情况

## 环境配置
### 一、基本环境
- 系统要求：Linux、Microsoft Windows 10.0.14393或更高版本
- Web运行环境：JDK 8或更高版本、Tomcat 9.0或更高版本、Gradle（自动配置SSM）
- Python运行环境：Python 3.6或更高版本（不支持Python 2.x）
- 数据库运行环境：MySQL或MariaDB

### 二、依赖环境
- binwalk：用于系统固件分析模块（对于Windows必须将binwalk安装在Windows Subsystem Linux中），https://github.com/ReFirmLabs/binwalk

### 三、部署方式
- 按照Java Web项目部署war包即可

## 计划中的功能
- 系统固件动态分析：整合QEMU开源组件。
- 基于Docker的快捷部署