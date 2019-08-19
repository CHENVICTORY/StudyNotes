# 自定义prograssView(自定义属性并且绘制其属性)

* 首先自定义属性标签值
        > 在values 下面新建一个attars.xml的文件 并在里面定义我们的属性


            <code><?xml version="1.0" encoding="UTF-8"?>
            <resources>

            <declare-styleable name="RoundProgressBar">
        <attr name="roundColor" format="color" />
        <attr name="roundProgressColor" format="color" />
        <attr name="roundWidth" format="dimension"></attr>
        <attr name="textColor" format="color" />
        <attr name="textSize" format="dimension" />
        <attr name="max" format="integer"></attr>
        <attr name="textIsDisplayable" format="boolean"></attr>
        <attr name="style">
            <enum name="STROKE" value="0"></enum>
            <enum name="FILL" value="1"></enum>
        </attr>
          </declare-styleable>

        </resources>