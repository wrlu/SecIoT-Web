# SecIoT
- IoT安全漏洞检测平台

## 主要功能
### 一、系统安全
#### 1、系统固件分析
- 分析Squashfs和JFFS2文件系统的固件
- 分析固件中第三方库的版本以及所涉及的漏洞CVE编号（目前仅支持OpenSSL）
- 分析固件中存在的平台配置风险，目前支持的功能：
    - 分析Linux固件中的用户情况
    - 分析Linux系统是否可通过Dropbear进行SSH远程登录
    - 分析Linux系统是否在Dropbear中配置了公钥
    - 分析Linux系统的计划任务（crontab）情况

### 二、应用安全
#### 1、安卓应用分析
- 分析Android应用所需的权限
- 分析Android应用常见平台风险，目前支持的功能：
    - Android组件暴露分析
    - Android SSL弱校验分析

#### 2、苹果应用分析（即将添加）
- 分析iOS应用所需的权限
- 分析iOS应用常见平台风险

### 三、传输安全
#### 1、传输流量分析
- 分析流量包中特定IP传输目标以及传输协议


## 环境配置
### 一、基本环境
- 系统要求：Linux、Microsoft Windows 10.0.14393或更高版本
- Web运行环境：JDK 8或更高版本、Tomcat 9.0或更高版本、Gradle（自动配置SSM）
- Python运行环境：Python 3.6或更高版本（不支持Python 2.x）
- 数据库运行环境：MySQL或MariaDB

### 二、依赖环境
- binwalk：用于系统固件分析模块（对于Windows必须将binwalk安装在Windows Subsystem Linux中），https://github.com/ReFirmLabs/binwalk
- jadx：用于安卓应用分析模块，https://github.com/skylot/jadx
- scapy：用于传输流量分析模块，https://github.com/secdev/scapy
- tcpdump：用于传输流量分析模块（仅Linux需要）

## 计划添加的功能
- 苹果应用分析：分析iOS平台的ipa文件
- 安卓动态分析功能：允许动态运行Android程序并利用Frida注入进行深度分析

## 贡献者和许可证
- 贡献者：SecIoT Web Developers，欢迎发起Pull Request
- 许可证：GPLv2
