package com.example.fiver.douandroidsdk.imageloader;
/*
 *  项目名:  DouVedio
 *  包名:  com.example.fiver.douandroidsdk.imageloader
 *  文件名:  ImageLoaderManager
 *  创建者:  YYC
 *  创建时间:  17/7/7 上午10:11
 *  描述:  初始化UniversalImageLoader,并用来加载图片
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.fiver.douandroidsdk.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImageLoaderManager {
    private static final int THREAD_COUNT = 4; //最多可有的线程数量
    private static final int PROPRITY = 2; //图片加载的优先级
    private static final int DISK_CACHE_SIZE = 50 * 1024;//最多图片缓存容量
    private static final int CONNECTION_TIME_OUT = 5 * 1000;//连接超时时间
    private static final int READ_TIME_OUT = 30 * 1000;//读取超时时间

    private static ImageLoader mImageLoader = null;
    private static ImageLoaderManager mInstance = null;

    private static ImageLoaderManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    //单例模式的私有构造方法
    private ImageLoaderManager(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.
                Builder(context)
                .threadPoolSize(THREAD_COUNT)  //配置图片下载的最大线程数量
                .threadPriority(Thread.NORM_PRIORITY - PROPRITY)
                .denyCacheImageMultipleSizesInMemory()//防止缓存多套尺寸图片
                .diskCacheSize(DISK_CACHE_SIZE)   //分配硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //Md5命名文件
                .tasksProcessingOrder(QueueProcessingType.FIFO)//图片下载顺序
                .defaultDisplayImageOptions(getDefaultOptions())//默认的图片加载option
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT,
                        READ_TIME_OUT))//设置图片加载器
                .writeDebugLogs()//debug环境下输出日志
                .build();
        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();
    }

    /**
     * 实现默认的options
     *
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)//使用的解码类型
                .decodingOptions(new BitmapFactory.Options())
                .build();
        return options;
    }

    public void displayImage(ImageView imageView, String url,
                             DisplayImageOptions options,
                             ImageLoadingListener listener) {
        if (mImageLoader != null) {
            mImageLoader.displayImage(url, imageView, options, listener);
        }
    }

    public void displayImage(ImageView imageView, String url, ImageLoadingListener listener) {
        displayImage(imageView, url, null, listener);

    }

    public void displayImage(ImageView imageView, String url) {
        displayImage(imageView, url, null);
    }
}
