# SecIoT
- IoT安全漏洞检测平台

## 主要功能
### 一、系统安全
#### 1、系统固件分析
- 分析Squashfs和JFFS2文件系统的固件
- 分析固件中第三方库的版本以及此版本存在的CVE漏洞，目前支持以下第三方库的检测：
    - OpenSSL
    - gzlib（即将添加）
    - busybox（即将添加）
    - miniUPnP（即将添加）
    - uclibc（即将添加）
    - OpenSSH（即将添加）
    - Dropbear（即将添加）
    - pcre（即将添加）
    - OpenLDAP（即将添加）
- 分析固件中存在的平台配置风险，目前支持的功能：
    - 分析Linux固件中的用户情况
    - 分析Linux系统是否可通过Dropbear进行SSH远程登录
    - 分析Linux系统是否在Dropbear中配置了公钥
    - 分析Linux系统的计划任务（crontab）情况

### 二、安卓应用安全
#### 1、Android应用静态分析
- 分析Android应用所需的权限
- 分析Android应用常见平台风险，目前支持的功能：
    - Android组件暴露分析
    - Android SSL弱校验分析

#### 2、Android应用动态分析（即将添加）
- 分析Android应用运行时调用的敏感API（即将添加）
- 分析Android应用运行时连接的IP地址（即将添加）
- 分析Android应用运行时进行的不安全的数据传输（即将添加）
- 分析Android应用运行时进行的文件写入（即将添加）
- 分析Android应用运行时进行的数据库写入（即将添加）

### 三、苹果应用安全
#### 2、苹果应用分析（即将添加）
- 分析iOS应用所需的权限
- 分析iOS应用常见平台风险

## 环境配置
### 一、基本环境
- 系统要求：Linux、Microsoft Windows 10.0.14393或更高版本
- Web运行环境：JDK 8或更高版本、Tomcat 9.0或更高版本、Gradle（自动配置SSM）
- Python运行环境：Python 3.6或更高版本（不支持Python 2.x）
- 数据库运行环境：MySQL或MariaDB

### 二、依赖环境
- binwalk：用于系统固件分析模块（对于Windows必须将binwalk安装在Windows Subsystem Linux中），https://github.com/ReFirmLabs/binwalk
- jadx：用于Android应用静态分析模块，https://github.com/skylot/jadx
- Frida：用于Android应用动态分析模块，https://github.com/frida/frida
- SecIoT Agent：对于Android应用动态分析，需要在目标Android手机上安装Agent应用程序，并授予该应用root权限，https://github.com/seciot/SecIoT-Agent-Android

## 贡献者和许可证
- 贡献者：SecIoT Web Developers，欢迎发起Pull Request
- 许可证：GPLv2
