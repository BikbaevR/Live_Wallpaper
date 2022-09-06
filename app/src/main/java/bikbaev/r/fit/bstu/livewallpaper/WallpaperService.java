package bikbaev.r.fit.bstu.livewallpaper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;

public class WallpaperService extends android.service.wallpaper.WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new WallpaperEngine();
    }

    class WallpaperEngine extends Engine {

        SurfaceHolder surfaceHolder;

        Handler handler;

        Runnable redrawRunnable = new Runnable() {
            @Override
            public void run() {
                draw();
                handler.postDelayed(this, 10);
            }
        };

        private void draw() {
            if(surfaceHolder != null) {
                try {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if(canvas != null) {
                        canvas.drawColor(Color.RED);
                    }
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception exception) {
                    Log.e("wallpaper", exception.getMessage(), exception);
                }
            }
        }

        public WallpaperEngine() {
            super();
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            this.surfaceHolder = surfaceHolder;
            handler = new Handler();


        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            surfaceHolder = holder;
        }

        @Override
        public void onSurfaceRedrawNeeded(SurfaceHolder holder) {
            super.onSurfaceRedrawNeeded(holder);
            //Canvas canvas = holder.lockCanvas();
            //canvas.drawColor(Color.RED);
            //holder.unlockCanvasAndPost(canvas);
            surfaceHolder = holder;
            draw();
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
        }
    }
}
