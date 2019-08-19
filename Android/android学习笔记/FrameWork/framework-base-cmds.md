 2016.9.7 
#安卓FrameWork代码学习： 
  * frameworks 包下面的东西： 
       * base--> cmd  这里面的东西都是用来处理命令行输入的  
            *  am 代表am命令输入
            *  input 代表input命令输入
            *  dumpstate  用来显示安卓系统的部分信息， 不过有些信息没有权限  详细解析见连接[http://blog.csdn.net/kevinx_xu/article/details/26253969](http://blog.csdn.net/kevinx_xu/article/details/26253969)  另见附件[file://dumpstate.txt]
            *  screencap 命令  screen -p 图片以png形式进行保存  screencap -p /data/data/hah.png
             将图片保存到data/data/hah.png



       * base-->core 
            * dubug 包 下面真的是一些关于是否调试的信息
            * java 包 下面是