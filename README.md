EditTextDropDown
================

#自定义编辑框下拉列表#
- 效果：类似Spinner的空间，区别在于点击一个EditText弹出的列表是紧跟在其下面的。列表是通过PopupWindow展现的。
- 弹出对话框核心代码

		private void showNumberListDialog() {
			pw = new PopupWindow(mListView, etNumber.getWidth() - 4, 200);
			
			// 点击外部可以被关闭
			//pw.setOutsideTouchable(true); 
			pw.setBackgroundDrawable(new BitmapDrawable());
			
			pw.setFocusable(true);// 设置PopupWindow允许获得焦点, 默认情况下popupWindow中的控件不可以获得焦点, 例外: Button, ImageButton..
			pw.showAsDropDown(etNumber, 2, -5);
		}
	
- 效果图
 ![Image text](http://raw.github.com/mingyuanchen/EditTextDropDown/master/images-folder/preview.gif)
		
