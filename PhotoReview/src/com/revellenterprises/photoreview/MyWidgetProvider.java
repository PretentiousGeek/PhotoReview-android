package com.revellenterprises.photoreview;

import android.appwidget.*;
import android.content.*;
import android.provider.*;
import android.app.*;
import android.widget.*;

public class MyWidgetProvider extends AppWidgetProvider
{

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{

		final int N = appWidgetIds.length;

		for (int i = 0; i < N; i++)
		{
			int appWidgetId = appWidgetIds[i];

			RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget);
				
			Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			
			PendingIntent pi = PendingIntent.getActivity(context,0,in,0);

			views.setOnClickPendingIntent(R.id.widgetImageView,pi);
				
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}

		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
}
