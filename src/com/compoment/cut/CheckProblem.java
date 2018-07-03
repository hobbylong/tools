package com.compoment.cut;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.compoment.remote.CheckProblemInterface;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;



public class CheckProblem extends UnicastRemoteObject implements CheckProblemInterface {




	public CheckProblem() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	int maxW = 0;
	int maxH = 0;

	public List<CompomentBean> analyseRelative( List<CompomentBean> oldbeans) throws RemoteException{
	
		
		 List<CompomentBean> beans=null;
		try {
			beans = deepCopy(oldbeans);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();
		CompomentBean maxBean = null;
		// 找出容器
		for (CompomentBean bean : beans) {
			if (bean.type.contains("Layout")) {
				if (bean.w >= maxW) {
					maxW = bean.w;
					maxBean = bean;
				}

				if (bean.h >= maxH) {
					maxH = bean.h;
					maxBean = bean;
				}

				layouts.add(bean);
			}
		}
		
		if(maxBean==null)
		{
			beans.clear();
			return null;
		}

		// 儿子找父亲 （子控件找容器）
		for (CompomentBean bean : beans) {
			if (!bean.type.contains("Layout")) {
				CompomentBean nearLayout = null;
				Integer minDistance = null;
				for (CompomentBean layout : layouts) {
					if (bean.x >= layout.x && bean.x <= layout.w + layout.x
							&& bean.y >= layout.y
							&& bean.y <= layout.h + layout.y) {
						if (minDistance == null) {
							minDistance = bean.x - layout.x;
							nearLayout = layout;
						} else {
							if (minDistance > bean.x - layout.x) {
								minDistance = bean.x - layout.x;
								nearLayout = layout;
							}
						}
					}
				}

				if (nearLayout != null)
					nearLayout.setChirld(bean);
			}

		}

		// 儿子找父亲（子容器找父容器）
		for (CompomentBean bean : layouts) {

			CompomentBean nearLayout = null;
			Integer minDistance = null;
			for (CompomentBean layout : layouts) {

				if (!layout.enname.equals(bean.enname) && bean.x >= layout.x
						&& bean.x <= layout.w + layout.x && bean.y >= layout.y
						&& bean.y <= layout.h + layout.y) {

					if (minDistance == null) {
						minDistance = bean.x - layout.x;
						nearLayout = layout;
					} else {
						if (minDistance > bean.x - layout.x) {
							minDistance = bean.x - layout.x;
							nearLayout = layout;
						}
					}

				}

			}

			if (nearLayout != null)
				nearLayout.setChirld(bean);

		}

		// LinearLayout RelativeLayout 儿子们的位置关系(排版方向 水平或垂直)
		for (CompomentBean bean : layouts) {
			if (bean.type.contains("Layout")) {
				if (bean.chirlds != null && bean.chirlds.size() > 1) {
					CompomentBean firstChirld = null;
					CompomentBean secondChirld = null;

					int count = 0;
					for (CompomentBean chirld : bean.chirlds) {
						if (count == 0) {
							firstChirld = chirld;
						} else if (count == 1) {
							secondChirld = chirld;
						}
						count++;
					}

					if (firstChirld.x > secondChirld.x) {
						if (firstChirld.x >= secondChirld.x + secondChirld.w) {
							bean.orientation = "horizontal";
							if (bean.type.contains("RelativeLayout")) {
								bean.gravity = "";
							} else if (bean.type.contains("LinearLayout")) {
								bean.gravity = "center_vertical";
							}

						} else {
							bean.orientation = "vertical";
						}
					} else if (firstChirld.x < secondChirld.x) {
						if (secondChirld.x >= firstChirld.x + firstChirld.w) {
							bean.orientation = "horizontal";

							if (bean.type.contains("RelativeLayout")) {
								bean.gravity = "";
							} else if (bean.type.contains("LinearLayout")) {
								bean.gravity = "center_vertical";
							}

						} else {
							bean.orientation = "vertical";
						}
					} else if (firstChirld.x == secondChirld.x) {
						bean.orientation = "vertical";

					}

				} else {
					bean.orientation = "horizontal";
					if (bean.type.contains("RelativeLayout")) {
						bean.gravity = "";
					} else if (bean.type.contains("LinearLayout")) {
						bean.gravity = "center_vertical";
					}

				}
			}

		}

		// RelativeLayout 儿子们的位置关系
		for (CompomentBean bean : layouts) {
			if (bean.type.equals("RelativeLayout")) {
				if (bean.chirlds != null && bean.chirlds.size() > 1) {

					if (bean.chirlds.size() == 2) {
						if (bean.orientation.equals("horizontal")) {

							CompomentBean chirld1 = bean.chirlds.get(0);
							CompomentBean chirld2 = bean.chirlds.get(1);
							if (chirld1.x < chirld2.x) {
								chirld1.relative += " android:layout_alignParentLeft=\"true\"  ";
								chirld2.relative += " android:layout_alignParentRight=\"true\"  ";
							} else if (chirld1.x > chirld2.x) {
								chirld2.relative += " android:layout_alignParentLeft=\"true\"  ";
								chirld1.relative += " android:layout_alignParentRight=\"true\"  ";
							}

							chirld1.relative += " android:layout_centerVertical=\"true\" ";

							chirld2.relative += " android:layout_centerVertical=\"true\" ";

						} else if (bean.orientation.equals("vertical")) {
							CompomentBean chirld1 = bean.chirlds.get(0);
							CompomentBean chirld2 = bean.chirlds.get(1);
							if (chirld1.y < chirld2.y) {
								chirld1.relative += " android:layout_alignParentTop=\"true\"   ";
								chirld2.relative += " android:layout_alignParentBottom=\"true\"  ";
							} else if (chirld1.y > chirld2.y) {

								chirld2.relative += " android:layout_alignParentTop=\"true\"  ";
								chirld1.relative += " android:layout_alignParentBottom=\"true\"  ";

							}

							chirld1.relative += " android:layout_centerHorizontal=\"true\" ";

							chirld2.relative += " android:layout_centerHorizontal=\"true\" ";

						}

					} else if (bean.chirlds.size() == 3) {

						if (bean.orientation.equals("horizontal")) {

							Collections.sort(bean.chirlds, comparatorX);

							int i = 0;
							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {
									chirld.relative += " android:layout_alignParentLeft=\"true\" ";

								} else if (i == 1) {
									chirld.relative += " android:layout_centerInParent=\"true\"  ";

								} else if (i == 2) {
									chirld.relative += " android:layout_alignParentRight=\"true\"  ";
								}

								chirld.relative += " android:layout_centerVertical=\"true\"";

								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						} else if (bean.orientation.equals("vertical")) {
							Collections.sort(bean.chirlds, comparatorY);

							int i = 0;
							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {
									chirld.relative += " android:layout_alignParentTop=\"true\"  ";

								} else if (i == 1) {
									chirld.relative += " android:layout_centerInParent=\"true\"  ";

								} else if (i == 2) {
									chirld.relative += " android:layout_alignParentBottom=\"true\"  ";
								}

								if (chirld.type.contains("Layout")) {
									chirld.relative += " android:layout_centerHorizontal=\"true\"";
								}
								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						}

					} else if (bean.chirlds.size() > 3) {

						if (bean.orientation.equals("horizontal")) {
							Collections.sort(bean.chirlds, comparatorX);

							int i = 0;
							CompomentBean toRightOf = null;
							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {
									chirld.relative += " android:layout_alignParentLeft=\"true\" android:layout_centerVertical=\"true\" ";
									toRightOf = chirld;
								} else if (i == bean.chirlds.size() - 1) {
									chirld.relative += " android:layout_alignParentRight=\"true\" android:layout_centerVertical=\"true\" ";
								} else {
									chirld.relative += " android:layout_toRightOf=\"@id/"
											+ toRightOf.enname
											+ "\" android:layout_centerVertical=\"true\" ";
									toRightOf = chirld;
								}
								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						} else if (bean.orientation.equals("vertical")) {
							Collections.sort(bean.chirlds, comparatorY);
							int i = 0;
							CompomentBean below = null;
							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {
									chirld.relative += " android:layout_alignParentTop=\"true\" android:layout_centerHorizontal=\"true\" ";
									below = chirld;
								} else if (i == bean.chirlds.size() - 1) {
									chirld.relative += " android:layout_alignParentBottom=\"true\" android:layout_centerHorizontal=\"true\" ";
								} else {
									chirld.relative += " android:layout_below=\"@id/"
											+ below.enname
											+ "\" android:layout_centerHorizontal=\"true\" ";

									below = chirld;
								}
								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						}
					}

				}
			}
		}

	

	
		
		
		return beans ;
	}
	
	
	 CompomentBean maxBean = null;
	 
	public  boolean check(List<CompomentBean> old) throws RemoteException {
		// Collections.sort(oldBeans, comparatorDate);
		
		
		try{
			List<CompomentBean> oldBeans=analyseRelative(old);
		int maxW = 0;
		int maxH = 0;
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();

		// 找出容器
		for (CompomentBean bean : oldBeans) {
			if (bean.type.contains("Layout")) {
				if (bean.w >= maxW) {
					maxW = bean.w;
					maxBean = bean;
				}

				if (bean.h >= maxH) {
					maxH = bean.h;
					maxBean = bean;
				}

				layouts.add(bean);
			}
		}
   
		if(oldBeans.size()>1 && maxBean.chirlds==null)
		{
			return false;
		}
		parent(maxBean);
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	
	public void parent(CompomentBean bean) {

	    
		//有	儿子
		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			

			for (CompomentBean chirld : bean.chirlds) {
				
				//这个儿子是容器 layout
				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					parent(chirld);

				} else {//这个儿子是非容器 
					
					chirld(chirld, bean);
				}
			}

		}

	}

	
	
	public void chirld(CompomentBean chirld, CompomentBean parent) {//这个儿子是非容器

	
		 
		if (chirld.type.equals("TextView")) {
			
		}

		if (chirld.type.equals("Button")) {
		
		}

		
		if (chirld.type.equals("EditText")) {
		

		}

		if (chirld.type.equals("CheckBox")) {
		

		}

		if (chirld.type.equals("ListView")) {
	
		}


		if (chirld.type.equals("ImageView")) {
			
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}
	
	

	Comparator<CompomentBean> comparatorX = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.x != s2.x) {
				return s1.x - s2.x;
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorY = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.y != s2.y) {
				return s1.y - s2.y;
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorDate = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};
	
	
	/**
     * 深层拷贝对象
     * 
     * @param src
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
   
    public List deepCopy(List src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
 
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List dest = (List) in.readObject();
        return dest;
    }
	
}
