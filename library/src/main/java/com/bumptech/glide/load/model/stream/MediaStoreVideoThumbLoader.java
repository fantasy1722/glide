package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.mediastore.MediaStoreUtil;
import com.bumptech.glide.load.data.mediastore.ThumbFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

public class MediaStoreVideoThumbLoader implements ModelLoader<Uri, InputStream> {
    private final Context context;

    MediaStoreVideoThumbLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public DataFetcher<InputStream> getDataFetcher(Uri model, int width, int height) {
        if (MediaStoreUtil.isThumbnailSize(width, height)) {
            return ThumbFetcher.buildVideoFetcher(context, model);
        } else {
            return null;
        }
    }

    @Override
    public boolean handles(Uri model) {
        return MediaStoreUtil.isMediaStoreVideoUri(model);
    }

    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

        @Override
        public ModelLoader<Uri, InputStream> build(Context context, MultiModelLoaderFactory multiFactory) {
            return new MediaStoreVideoThumbLoader(context);
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }
}
