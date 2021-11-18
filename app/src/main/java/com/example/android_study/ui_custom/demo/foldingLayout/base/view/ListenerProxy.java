package com.example.android_study.ui_custom.demo.foldingLayout.base.view;

import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.slidingpanelayout.widget.SlidingPaneLayout.PanelSlideListener;

class ListenerProxy implements DrawerListener, PanelSlideListener {

	private DrawerListener mDrawerListener;
	private PanelSlideListener mPanelSlideListener;

	public ListenerProxy(DrawerListener drawerListener,
						 PanelSlideListener panelSlideListener) {
		mDrawerListener = drawerListener;
		mPanelSlideListener = panelSlideListener;
	}

	public DrawerListener getDrawerListener() {
		return mDrawerListener;
	}

	public void setDrawerListener(DrawerListener drawerListener) {
		this.mDrawerListener = drawerListener;
	}

	public PanelSlideListener getPanelSlideListener() {
		return mPanelSlideListener;
	}

	public void setPanelSlideListener(PanelSlideListener panelSlideListener) {
		this.mPanelSlideListener = panelSlideListener;
	}

	@Override
	public void onDrawerClosed(View drawerView) {
		if (null != mDrawerListener) {
			mDrawerListener.onDrawerClosed(drawerView);
		}
	}

	@Override
	public void onDrawerOpened(View drawerView) {
		if (null != mDrawerListener) {
			mDrawerListener.onDrawerOpened(drawerView);
		}
	}

	@Override
	public void onDrawerSlide(View drawerView, float slideOffset) {
		if (drawerView instanceof BaseFoldingLayout) {
			((BaseFoldingLayout) drawerView).setFoldFactor(1 - slideOffset);
		}

		if (null != mDrawerListener) {
			mDrawerListener.onDrawerSlide(drawerView, slideOffset);
		}
	}

	@Override
	public void onDrawerStateChanged(int state) {
		if (null != mDrawerListener) {
			mDrawerListener.onDrawerStateChanged(state);
		}
	}

	@Override
	public void onPanelSlide(View v, float slideOffset) {
		if (v instanceof BaseFoldingLayout) {
			((BaseFoldingLayout) v).setFoldFactor(1 - slideOffset);
		}

		if (null != mPanelSlideListener) {
			mPanelSlideListener.onPanelSlide(v, slideOffset);
		}
	}

	@Override
	public void onPanelOpened(View v) {
		if (null != mPanelSlideListener) {
			mPanelSlideListener.onPanelOpened(v);
		}
	}

	@Override
	public void onPanelClosed(View v) {
		if (null != mPanelSlideListener) {
			mPanelSlideListener.onPanelClosed(v);
		}
	}

}
