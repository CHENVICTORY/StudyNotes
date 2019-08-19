# 自定义View  及自定义属性

*  一 : 首先添加自定义属性  res/valure 下面进行添加 新建attrs.xml文件
 

           <?xml version="1.0" encoding="utf-8"?>
            <resources>
             <declare-styleable name="myViewDefinedAttr"> 
           <attr name="attr1" format="boolean"/> 
           <attr name="attr2" format="integer"/>
            </declare-styleable> 
            </resources>
 在这里定义了名称为myViewDefinedAttr 的属性列表 name会在以后的view当中使用  其中name为属性名称,format为属性类型, 具体支持的类型为: boolean, string, integer, dimension, float, reference, color, fraction, enum, flag及其混合

* 二: 自定义view当中获取属性值:
      public class MyView extends View {

    private boolean attr1;
    private int     attr2;
    
    public MyView(Context context){
        super(context);
    }
    
    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
        getAttrs(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        getAttrs(context, attrs);
    }
    
    /**
     * 得到属性值
     * 
     * @param context
     * @param attrs
     */
    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.myViewDefinedAttr);
        attr1 = ta.getBoolean(R.styleable.myViewDefinedAttr_attr1, true);
        attr2 = ta.getInt(R.styleable.myViewDefinedAttr_attr2, 0);
        ta.recycle();
    }
     }


* 三: 调用自定义的属性值:
     <     RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:myViewXmlns="http://schemas.android.com/apk/res/com.trinea.mypackage"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.trinea.android.common.view.MyView
        android:id="@+id/app_list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fastScrollEnabled="true"
        myViewXmlns:attr1="false"
        myViewXmlns:attr2="1"
        android:focusable="true" />

        ……
      </RelativeLayout>
注意名称的使用 一定要使用全名称路径 同时要加入命名空间  路径前面的名称和android当中的一样, 后面要改为自己的包名

* 4、自定义属性的类型

format表示的属性类型可以为boolean, string, integer, dimension, float, reference, color, fraction, enum, flag及其混合。
(1) boolean表示布尔值，调用如 xx:attr1="false"
(2) integer表示整型，调用如 xx:attr1="1"
(3) dimension表示尺寸值，调用如 xx:attr1="42dp"
(4) float表示浮点型，调用如 xx:attr1="0.7"
(5) color表示颜色值，调用如 xx:attr1="#00FF00"
(6) string表示字符串，调用如 xx:attr1="#adbddd"
(7) reference表示参考某一资源id，调用如 xx:attr1 = "@drawable/图片ID"
(8) fraction表示百分数，调用如 xx:attr1="30%"
以上类型定义都为<attr name="attr1" format="xxxtype"/>

(9) enum表示枚举值，定义为

<attr name="enum_attr">
      <enum name="horizontal" value="0" />
      <enum name="vertical" value="1" />
</attr>

调用如 xx:attr1="horizontal"

(10) flag表示位或运算，定义为
复制代码

<attr name="windowSoftInputMode">
    <flag name = "stateUnspecified" value = "0" />
    <flag name = "stateUnchanged" value = "1" />
    <flag name = "stateHidden" value = "2" />
    <flag name = "stateAlwaysHidden" value = "3" />
    <flag name = "stateVisible" value = "4" />
    <flag name = "stateAlwaysVisible" value = "5" />
    <flag name = "adjustUnspecified" value = "0x00" />
    <flag name = "adjustResize" value = "0x10" />
    <flag name = "adjustPan" value = "0x20" />
    <flag name = "adjustNothing" value = "0x30" />
 </attr> 

复制代码

调用如：xx:attr1="stateUnspecified | stateUnchanged　|　stateHidden"

(11) 混合类型，定义为

<declare-styleable name = "combine_type">
    <attr name = "background" format = "reference|color" />
</declare-styleable>

调用如 xx:attr1 = "@drawable/图片ID|#DDFF00"