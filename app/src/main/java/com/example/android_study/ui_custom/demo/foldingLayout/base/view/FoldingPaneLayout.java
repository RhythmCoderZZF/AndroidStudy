/*
 * Copyright (C) 2013 Priboi Tiberiu
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android_study.ui_custom.demo.foldingLayout.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.slidingpanelayout.widget.SlidingPaneLayout;

/**
 * FoldingPaneLayout change the sliding effect with folding effect of
 * SlidingPaneLayout
 * 
 */
public class FoldingPaneLayout extends SlidingPaneLayout {

	private ListenerProxy mListenerProxy;

	private BaseFoldingLayout mFoldingLayout;

	public FoldingPaneLayout(Context context) {
		this(context, null);
	}

	public FoldingPaneLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FoldingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mFoldingLayout = new BaseFoldingLayout(getContext(), attrs, defStyle);
		mListenerProxy = new ListenerProxy(null, null);
		super.setPanelSlideListener(mListenerProxy);
	}

	public BaseFoldingLayout getFoldingLayout() {
		return mFoldingLayout;
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		View child = getChildAt(0);
		if (child != null) {
			removeView(child);
			mFoldingLayout.addView(child);
			ViewGroup.LayoutParams layPar = child.getLayoutParams();
			addView(mFoldingLayout, 0, layPar);
		}
	}

	@Override
	public void setPanelSlideListener(PanelSlideListener listener) {
		mListenerProxy.setPanelSlideListener(listener);
	}
}
