# enchat
![](https://raw.githubusercontent.com/wangchenyan/PonyMusic/master/app/src/main/res/drawable-xxhdpi/ic_launcher.png)

## 项目
### 公开API
- 在线音乐：[百度音乐](http://mrasong.com/a/baidu-mp3-api-full)
- 天气数据：[高德地图](http://lbs.amap.com/)

### 开源技术
- [okhttp-utils](https://github.com/hongyangAndroid/okhttp-utils)
- [Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)


```java
@Override
protected void onDraw(Canvas canvas) {
    // 1.绘制顶部虚线
    mTopLine.setBounds(0, 0, getWidth(), mTopLineHeight);
    mTopLine.draw(canvas);
    // 2.绘制黑胶唱片外侧半透明边框
    mCoverBorder.setBounds(mDiscPoint.x - mCoverBorderWidth, mDiscPoint.y - mCoverBorderWidth,
            mDiscPoint.x + mDiscBitmap.getWidth() + mCoverBorderWidth, mDiscPoint.y +
                    mDiscBitmap.getHeight() + mCoverBorderWidth);
    mCoverBorder.draw(canvas);
    // 3.绘制黑胶
    // 设置旋转中心和旋转角度，setRotate和preTranslate顺序很重要
    mDiscMatrix.setRotate(mDiscRotation, mDiscCenterPoint.x, mDiscCenterPoint.y);
    // 设置图片起始坐标
    mDiscMatrix.preTranslate(mDiscPoint.x, mDiscPoint.y);
    canvas.drawBitmap(mDiscBitmap, mDiscMatrix, null);
    // 4.绘制封面
    mCoverMatrix.setRotate(mDiscRotation, mCoverCenterPoint.x, mCoverCenterPoint.y);
    mCoverMatrix.preTranslate(mCoverPoint.x, mCoverPoint.y);
    canvas.drawBitmap(mCoverBitmap, mCoverMatrix, null);
    // 5.绘制指针
    mNeedleMatrix.setRotate(mNeedleRotation, mNeedleCenterPoint.x, mNeedleCenterPoint.y);
    mNeedleMatrix.preTranslate(mNeedlePoint.x, mNeedlePoint.y);
    canvas.drawBitmap(mNeedleBitmap, mNeedleMatrix, null);
}
```
歌词绘制流程
```java
@Override
protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    // 中心Y坐标
    float centerY = getHeight() / 2 + mTextSize / 2 + mAnimOffset;

    // 无歌词文件
    if (!hasLrc()) {
        float centerX = (getWidth() - mCurrentPaint.measureText(label)) / 2;
        canvas.drawText(label, centerX, centerY, mCurrentPaint);
        return;
    }

    // 画当前行
    String currStr = mLrcTexts.get(mCurrentLine);
    float currX = (getWidth() - mCurrentPaint.measureText(currStr)) / 2;
    canvas.drawText(currStr, currX, centerY, mCurrentPaint);

    // 画当前行上面的
    for (int i = mCurrentLine - 1; i >= 0; i--) {
        String upStr = mLrcTexts.get(i);
        float upX = (getWidth() - mNormalPaint.measureText(upStr)) / 2;
        float upY = centerY - (mTextSize + mDividerHeight) * (mCurrentLine - i);
        // 超出屏幕停止绘制
        if (upY - mTextSize < 0) {
            break;
        }
        canvas.drawText(upStr, upX, upY, mNormalPaint);
    }

    // 画当前行下面的
    for (int i = mCurrentLine + 1; i < mLrcTimes.size(); i++) {
        String downStr = mLrcTexts.get(i);
        float downX = (getWidth() - mNormalPaint.measureText(downStr)) / 2;
        float downY = centerY + (mTextSize + mDividerHeight) * (i - mCurrentLine);
        // 超出屏幕停止绘制
        if (downY > getHeight()) {
            break;
        }
        canvas.drawText(downStr, downX, downY, mNormalPaint);
    }
}
```
