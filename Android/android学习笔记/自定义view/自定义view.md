#  自定义view基本上分为两种
    > view继承自viewGrop 是安卓已有View的集合
    > view是自己绘制出来的,


# 一 绘制自定义view

* 这里就主要使用两个方法:
     * view当中的onMeasure(主要用来测量当中控件的大小)
     * onDraw(将view当中要显示的东西进行绘制进行展示)

---
这里可以将view理解为一张白纸,进行回话.那么需要解决的问题就是我们把画画在哪里,怎么画, 图案哪里来?
---

* 步骤记录
      > 白纸 只要我们继承view 在onDraw(Canvas canvas)中的canvas就是我们所说的白纸
     <code>public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
        // canvas 即为白纸
        super.onDraw(canvas);
    }`           
   
      > 图案 这里的图案就是由图片和文字组成 定义一个Bitmap 和一个String 类的成员变量


      <code>private String mName ;

       mName = "这里直接赋值";

       mBitmap = BitmapFactory.decodeResource(getResource
       (),R.drawable.ic_launcher) 
     
      > 计算位置 所以最核心的就是计算绘制的位置,计算位置就需要先测量自身位置的大小 需要重写onMeasure方法 先看一下google怎么写的

---
* 只能说着代码太烂了  一个textView就写了上千行
       <code>
            @Override
       protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

       int widthMode = MeasureSpec.getMode(widthMeasureSpec);
       int heightMode = MeasureSpec.getMode(heightMeasureSpec);
       int widthSize = MeasureSpec.getSize(widthMeasureSpec);
       int heightSize = MeasureSpec.getSize(heightMeasureSpec);
 
    int width;
    int height;
 
    BoringLayout.Metrics boring = UNKNOWN_BORING;
    BoringLayout.Metrics hintBoring = UNKNOWN_BORING;
 
    if (mTextDir == null) {
        mTextDir = getTextDirectionHeuristic();
    }
 
    int des = -1;
    boolean fromexisting = false;
 
    if (widthMode == MeasureSpec.EXACTLY) {
        // Parent has told us how big to be. So be it.
        width = widthSize;
    } else {
        if (mLayout != null && mEllipsize == null) {
            des = desired(mLayout);
        }
 
        if (des < 0) {
            boring = BoringLayout.isBoring(mTransformed, mTextPaint, mTextDir, mBoring);
            if (boring != null) {
                mBoring = boring;
            }
        } else {
            fromexisting = true;
        }
 
        if (boring == null || boring == UNKNOWN_BORING) {
            if (des < 0) {
                des = (int) FloatMath.ceil(Layout.getDesiredWidth(mTransformed, mTextPaint));
            }
            width = des;
        } else {
            width = boring.width;
        }
 
        final Drawables dr = mDrawables;
        if (dr != null) {
            width = Math.max(width, dr.mDrawableWidthTop);
            width = Math.max(width, dr.mDrawableWidthBottom);
        }
 
        if (mHint != null) {
            int hintDes = -1;
            int hintWidth;
 
            if (mHintLayout != null && mEllipsize == null) {
                hintDes = desired(mHintLayout);
            }
 
            if (hintDes < 0) {
                hintBoring = BoringLayout.isBoring(mHint, mTextPaint, mTextDir, mHintBoring);
                if (hintBoring != null) {
                    mHintBoring = hintBoring;
                }
            }
 
            if (hintBoring == null || hintBoring == UNKNOWN_BORING) {
                if (hintDes < 0) {
                    hintDes = (int) FloatMath.ceil(Layout.getDesiredWidth(mHint, mTextPaint));
                }
                hintWidth = hintDes;
            } else {
                hintWidth = hintBoring.width;
            }
 
            if (hintWidth > width) {
                width = hintWidth;
            }
        }
 
        width += getCompoundPaddingLeft() + getCompoundPaddingRight();
 
        if (mMaxWidthMode == EMS) {
            width = Math.min(width, mMaxWidth * getLineHeight());
        } else {
            width = Math.min(width, mMaxWidth);
        }
 
        if (mMinWidthMode == EMS) {
            width = Math.max(width, mMinWidth * getLineHeight());
        } else {
            width = Math.max(width, mMinWidth);
        }
 
        // Check against our minimum width
        width = Math.max(width, getSuggestedMinimumWidth());
 
        if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(widthSize, width);
        }
    }
 
    int want = width - getCompoundPaddingLeft() - getCompoundPaddingRight();
    int unpaddedWidth = want;
 
    if (mHorizontallyScrolling) want = VERY_WIDE;
 
    int hintWant = want;
    int hintWidth = (mHintLayout == null) ? hintWant : mHintLayout.getWidth();
 
    if (mLayout == null) {
        makeNewLayout(want, hintWant, boring, hintBoring,
                      width - getCompoundPaddingLeft() - getCompoundPaddingRight(), false);
    } else {
        final boolean layoutChanged = (mLayout.getWidth() != want) ||
                (hintWidth != hintWant) ||
                (mLayout.getEllipsizedWidth() !=
                        width - getCompoundPaddingLeft() - getCompoundPaddingRight());
 
        final boolean widthChanged = (mHint == null) &&
                (mEllipsize == null) &&
                (want > mLayout.getWidth()) &&
                (mLayout instanceof BoringLayout || (fromexisting && des >= 0 && des <= want));
 
        final boolean maximumChanged = (mMaxMode != mOldMaxMode) || (mMaximum != mOldMaximum);
 
         if (layoutChanged || maximumChanged) {
            if (!maximumChanged && widthChanged) {
                mLayout.increaseWidthTo(want);
            } else {
                makeNewLayout(want, hintWant, boring, hintBoring,
                        width - getCompoundPaddingLeft() - getCompoundPaddingRight(), false);
            }
        } else {
            // Nothing has changed
        }
    }
 
    if (heightMode == MeasureSpec.EXACTLY) {
        // Parent has told us how big to be. So be it.
        height = heightSize;
        mDesiredHeightAtMeasure = -1;
    } else {
        int desired = getDesiredHeight();
 
        height = desired;
        mDesiredHeightAtMeasure = desired;
 
        if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desired, heightSize);
        }
    }
 
    int unpaddedHeight = height - getCompoundPaddingTop() - getCompoundPaddingBottom();
    if (mMaxMode == LINES && mLayout.getLineCount() > mMaximum) {
        unpaddedHeight = Math.min(unpaddedHeight, mLayout.getLineTop(mMaximum));
    }
 
    /*
     * We didn't let makeNewLayout() register to bring the cursor into view,
     * so do it here if there is any possibility that it is needed.
     */
    if (mMovement != null ||
        mLayout.getWidth() > unpaddedWidth ||
        mLayout.getHeight() > unpaddedHeight) {
        registerForPreDraw();
    } else {
        scrollTo(0, 0);
    }
 
    setMeasuredDimension(width, height);
      }
       </code>

---
    > 在定义所要绘制的界面之前 需要先了解一下参数 
   
     public static class MeasureSpec {
    private static final int MODE_SHIFT = 30;
    private static final int MODE_MASK  = 0x3 << MODE_SHIFT;
    public static final int UNSPECIFIED = 0 << MODE_SHIFT;
    public static final int EXACTLY     = 1 << MODE_SHIFT;
    public static final int AT_MOST     = 2 << MODE_SHIFT;
    public static int makeMeasureSpec(int size, int mode) {
        if (sUseBrokenMakeMeasureSpec) {
            return size + mode;
        } else {
            return (size & ~MODE_MASK) | (mode & MODE_MASK);
        }
    }
    public static int getMode(int measureSpec) {
        return (measureSpec & MODE_MASK);
    }
    public static int getSize(int measureSpec) {
        return (measureSpec & ~MODE_MASK);
    }
     }

* 在onMeasure当中的两个参数MeasuredWidth MeasuredHight 两个参数都是由30位的二进制数组成的,其中前两位表示绘制时的绘制模式 绘制的大小都是由父容器所决定的
   1. 0代表 unspecified  模糊不定
   2. 1代表Exactly  代表确定
   3. 2 代表AT_most  所能取到的最大值
      


* 重写onMeasure方法:
 
             <code> protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)         {
        //这里方法套路都是一样，不管三七 二十一，上来就先把mode 和 size 获取出来。
 
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
3/30/2016 2:55:22 PM 3/30/2016 2:55:24 PM 3/30/2016 2:55:25 PM 3/30/2016 2:55:25 PM 
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
  
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
          //View 真正需要显示的大小

        int width = 0, height = 0;

        //这里是去测量字体大小

         measureText();

          //字体宽度加图片宽度取最大宽度，这里因为字体和图片是上下排列

          int contentWidth = Math.max(mBoundText.width(), mIconNormal.getWidth());

         // 我们渴望得到的宽度

         int desiredWidth = getPaddingLeft() + getPaddingRight() + contentWidth;

        //重点来了，判断模式，这个模式哪里来的呢，就是在编写xml的时候，设置的layout_width

         switch (widthMode) {
         //如果是AT_MOST，不能超过父View的宽度

          case MeasureSpec.AT_MOST:

              width = Math.min(widthSize, desiredWidth);

              break;
              //如果是精确的，好说，是多少，就给多少；
          case MeasureSpec.EXACTLY:
              width = widthSize;
              break;
              //这种情况，纯属在这里打酱油的，可以不考虑
          case MeasureSpec.UNSPECIFIED://我是路过的
              width = desiredWidth;
              break;
         }
        int contentHeight = mBoundText.height() + mIconNormal.getHeight();

         int desiredHeight = getPaddingTop() + getPaddingBottom() + contentHeight;

         switch (heightMode) {

          case MeasureSpec.AT_MOST:
              height = Math.min(heightSize, desiredHeight);
              break;
          case MeasureSpec.EXACTLY:
              height = heightSize;
              break;
          case MeasureSpec.UNSPECIFIED:
              height = contentHeight;
              break;
        }
        //最后不要忘记了，调用父类的测量方法
          setMeasuredDimension(width, height);
         }