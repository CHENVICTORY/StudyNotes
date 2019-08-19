#gradle使用
@(Gradle使用)[基本配置|配置含义]

-------
** 一 基本配置
  - bulild配置
  ```
  buildscript {
 repositories {
     jcenter() 
 }
 dependencies {
     classpath 'com.android.tools.build:gradle:1.2.3'
 } 
}
```