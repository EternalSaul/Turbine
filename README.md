一开始这个项目push到这里只是一个随意行为，由于我发现有些朋友可能对这个项目比较感兴趣，这个项目里用了一些很非主流的库，不太容易找到，所以我从新修改了这个项目，把它作为一个Maven项目，有几点需要注意的：

1.这个小程序里的.java文件用的是GBK编码
>这个情况由于当时我的同学写了界面的一些部分，它没注意换成utf-8编码于是就直接用GBK了

2.这个小程序会在我的电脑/文档里生成一个Turbine文件夹用于保存数据
>>数据包括磁力链接解析的种子数据，还有程序的用户下载数据

3.这个项目中MagnetService,会去访问Turbine服务器：localhost:6088
>>这个服务器的源码是TurbienServer,完全是为了做测试用的服务器，你可以在我的仓库里找到这个项目，代码非常简单，默认逻辑是如果在本地的TurbineServer里没有找到磁力链接对应的种子文件，那么就找Vezu，你可以修改这个逻辑

4.这个项目不支持Http下载
>>当然这个比较简单，你可以自行扩充，当然如果我有时间，我也会随意的更新一下





# 1 系统功能说明

## 1.1      系统简介

Turbine（旋涡）是一款基于p2p的下载器软件，它可以解析基于bittorrent协议的种子文件，以及当前非常流行的磁力链接，通过磁力链接Turbine可以接入自身的服务器通过磁力链接的SHA码搜索对应的种子文件，如果自身服务器搜索不到则通过Vezu平台（一个种子库）搜索种子文件，搜索到种子文件以后，通过http获取它到本地，并打开解析这个种子，然后通过bittorrent协议完成对种子文件所索引的文件下载。期间Turbine可以显示下载进度，可以断点续传，打开文件所在文件夹，文件下载完成后它会被保存在已完成界面下，可以随时查看已完成的下载任务，而已完成和正在下载的任务都会被持久化保存在本地的持久化文件中，保证历史记录的存在。另外Turbine还支持删除任务。如图1.1所示

![1](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/1.png) 

![1.1](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/1.1.png)

图1.1 Turbine下载和已完成界面

## 1.2      系统主要功能  

### 1.2.1通过种子文件下载

通过点击新建按钮，打开索引界面，选择浏览一个种子文件和下载保存目录，点击下载后如图1.2,点击确定下载则Turbine会开始从bittorrent tracker里获取peer组，并开始交换数据。

 ![2](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/2.png)

图1.2以种子文件开始下载任务

### 1.2.2通过磁力链接下载

磁力链接是今年流行的下载方式，由于种子很容易被屏蔽，于是因特网上在今年来都开始以磁力链接代替种子的传播，磁力链接指定了一个种子所指的具体文件的sha摘要码，如:magnet:?xt=urn:btih:11aabbec897260de25a71f149712114bf9e38ddf,在下载界面中输入磁力链接，点击下载，则Turbine首先会向Turbine服务器以HTTP请求，以磁力链接的sha码请求一个种子文件，若服务器不存在则会向比较慢的Vezu种子库，以sha码所对应16进制数字的base32编码形式请求一个种子文件，若找到了种子文件则Turbine以http获取它并开始解析，等待用户确定下载。如图1.3和图1.4，一个美剧纸牌屋的磁力链接下载。

 ![3](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/3.png)

1.3解析磁力链接

 

 ![4](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/4.png)

1.4由磁力链接取得种子成功

### [1.2.3]()查看文件保存位置

Turbine可以在已下载和正在下载的任务中的打开文件按钮上，直接点击进入文件下载目录，如我点击了图1.1的ubutu的下载目录，windows打开它的文件系统，并浏览到对应目录。如图1.5。

 ![5](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/5.png)

图1.5文件浏览

### 1.2.4 删除下载任务

Turbine可以删除正在下载的未完成任务，如我把图1.1中已下载中的纸牌屋下载任务删除，则如图1.6所示，任务被删除了。

 ![6](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/6.png)

图1.6正在下载任务删除

### 1.2.5删除已下载的任务

Turbine可以删除已下载任务，如我把图1.1中已下载中的一个ubuntu任务删除，则如图1.7所示，任务被删除了。

 ![7](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/7.png)

图1.7已下载任务删除

### 1.2.6暂停和继续下载

Turbine可以暂停任务，比如我暂停图1.6中的ubuntu下载，如图1.8可以看到暂停按钮变成了继续，而任务的下载速度和时间都清0了,而点击继续下载又从断点开始

​     ![8.1](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/8.1.png)

![8](https://github.com/EternalSaul/Turbine/raw/master/turbine_img/8.png)

图1.8暂停下载