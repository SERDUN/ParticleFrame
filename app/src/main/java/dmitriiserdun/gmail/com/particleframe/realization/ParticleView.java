package dmitriiserdun.gmail.com.particleframe.realization;

import android.content.Context;
import android.util.AttributeSet;

import dmitriiserdun.gmail.com.particleframe.engine.RenderView;

/**
 * Created by dmitro on 31.12.17.
 */

public class ParticleView extends RenderView {
    public ParticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        screen=new ParticleScreen(context);
    }


}
