package com.compoment.db.tabledocinterfacedoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class DBTableRelativePanel extends JPanel implements MouseListener,
		MouseMotionListener {

	Graphics2D g2;
	public List<TableBean> tables;
	
	Point startPoint;
	Point endPoint;
	TableColumnBean startColumnBean;
	public List<TableColumnBean> latestRelateColumnBean;
	
	

	public DBTableRelativePanel() {
		tables = new ArrayList();
		latestRelateColumnBean=new ArrayList();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	
	}

	@Override
	public void paintComponent(Graphics pGraphics) {
		super.paintComponent(pGraphics);
		Graphics2D g2 = (Graphics2D) pGraphics;
		// g2.scale(aZoom, aZoom);
		Rectangle2D bounds = getBounds();

		for (TableBean table : tables) {

			// 画Table名字
			if(table.isMainTable)
			{
			g2.setColor(Color.green);
			}else
			{
				g2.setColor(Color.black);
			}
			g2.setFont(new Font("宋体", Font.BOLD, 11)); // 改变字体大小
			g2.drawString(table.tableCnName + "(" + table.tableEnName + ""
					+ table.id + ")", table.tableCnNameX, table.tableCnNameY);

			// 画Table边框
			// top
			g2.draw(new Line2D.Double(new Point2D.Double(table.x, table.y),
					new Point2D.Double(table.x1, table.y)));
			// bottom
			g2.draw(new Line2D.Double(new Point2D.Double(table.x, table.y1),
					new Point2D.Double(table.x1, table.y1)));
			// left
			g2.draw(new Line2D.Double(new Point2D.Double(table.x, table.y),
					new Point2D.Double(table.x, table.y1)));
			// right
			g2.draw(new Line2D.Double(new Point2D.Double(table.x1, table.y),
					new Point2D.Double(table.x1, table.y1)));

			for (TableColumnBean column : table.columns) {
				column.belongWhichTable=table;
				// 背景色
				if ("left".equals(column.leftClickSelected)) {
					g2.setColor(Color.red);
					g2.fillRect(column.x, column.y, (column.x1 - column.x)/2,
							column.y1 - column.y);

				}else {
					g2.setColor(Color.LIGHT_GRAY);
					g2.fillRect(column.x, column.y, (column.x1 - column.x)/2,
							column.y1 - column.y);

				}
				
				if ("right".equals(column.rightClickSelected)) {
					g2.setColor(Color.green);
					g2.fillRect(column.x+(column.x1 - column.x)/2, column.y, (column.x1 - column.x)/2,
							column.y1 - column.y);

				} else {
					g2.setColor(Color.LIGHT_GRAY);
					g2.fillRect(column.x+(column.x1 - column.x)/2, column.y, (column.x1 - column.x)/2,
							column.y1 - column.y);

				}

				// 画Column边框
				g2.setColor(Color.black);
				// top
				g2.draw(new Line2D.Double(
						new Point2D.Double(column.x, column.y),
						new Point2D.Double(column.x1, column.y)));
				// bottom
				g2.draw(new Line2D.Double(new Point2D.Double(column.x,
						column.y1), new Point2D.Double(column.x1, column.y1)));
				// left
				g2.draw(new Line2D.Double(
						new Point2D.Double(column.x, column.y),
						new Point2D.Double(column.x, column.y1)));
				// right
				g2.draw(new Line2D.Double(new Point2D.Double(column.x1,
						column.y), new Point2D.Double(column.x1, column.y1)));

				// 画文字
				g2.setFont(new Font("宋体", Font.BOLD, 11)); // 改变字体大小
				g2.setColor(Color.black);
				g2.drawString(column.columnCnName, column.columnCnNameX,
						column.columnCnNameY);
				g2.drawString(column.columnEnName, column.columnEnNameX,
						column.columnEnNameY);
				g2.drawString(column.type, column.typeX, column.typeY);
				g2.drawString(column.key, column.keyX, column.keyY);

			}

		}

		for (TableBean table : tables) {
			for (TableColumnBean column : table.columns) {

				// 画连线
				if (column.relateColumnBeans != null
						&& column.relateColumnBeans.size() > 0) {
					for (TableColumnBean relateColumn : column.relateColumnBeans) {

						g2.draw(new Line2D.Double(new Point2D.Double(column.x
								+ (column.x1 - column.x) / 2, column.y
								+ (column.y1 - column.y) / 2),
								new Point2D.Double(relateColumn.x
										+ (relateColumn.x1 - relateColumn.x)
										/ 2, relateColumn.y
										+ (relateColumn.y1 - relateColumn.y)
										/ 2)));
					}
				}
			}
		}
	}
	
	
	
	 public Dimension getPreferredSize() {
		 Dimension theSize = new Dimension(3000, 3000);
         return theSize;
 }

	public void cleanDBTables() {
		tables.clear();
		tables = new ArrayList();
		this.repaint();
	}

	// 选中的Table
	public void setDBTables(List<TableBean> interfaceBeans) {
		tables = new ArrayList();

		int x = 0;
		int y = 0;
		int everyTableYModify = 20;

		int leftspace = 3;
		int rightspace = 3;
		int topspace = 3;
		int bottomspace = 3;

		for (TableBean interfaceBean : interfaceBeans) {

			if (interfaceBean.selected == false)
				continue;

			// 数据表
			TableBean tableBean = new TableBean();
			tableBean.isMainTable=interfaceBean.isMainTable;
			tableBean.tableCnName = interfaceBean.tableCnName;// 表中文名
			tableBean.tableEnName = interfaceBean.tableEnName;// 表英文名
			tableBean.id = interfaceBean.id;// 表编号
			tableBean.columns = new ArrayList();// 列数组

			if (tables != null && tables.size() > 0) {// 已经有首个表（非首表）

				y = tables.get(tables.size() - 1).y1 + bottomspace
						+ everyTableYModify;
				x = tables.get(tables.size() - 1).x;
			} else {// 首表
				y = 0 + everyTableYModify;
				x = 0;
			}
			tableBean.y = y;
			tableBean.x = x;

			tableBean.tableCnNameX = x;
			tableBean.tableCnNameY = y - 5;

			List<TableColumnBean> rows = interfaceBean.columns;
			for (TableColumnBean row : rows) {
				// 列

				if (tableBean.columns != null && tableBean.columns.size() > 0) {// 已经有首列（非首列）
					TableColumnBean firstColumnBean = tableBean.columns.get(0);
					y = firstColumnBean.y;
					x = tableBean.columns.get(tableBean.columns.size() - 1).x1;
				} else {// 首列
					y = y;
					x = x;
				}

				TableColumnBean tableColumnBean = new TableColumnBean();

				tableColumnBean.y = y;
				tableColumnBean.x = x;

				tableColumnBean.setColumnCnName(row.columnCnName);
				tableColumnBean.columnCnNameX = x + rightspace;
				tableColumnBean.columnCnNameY = y
						+ tableColumnBean.columnCnNameHeight + bottomspace;

				tableColumnBean.setColumnEnName(row.columnEnName);
				tableColumnBean.columnEnNameX = x + rightspace;
				y = tableColumnBean.columnCnNameY
						+ tableColumnBean.columnCnNameHeight + bottomspace;
				tableColumnBean.columnEnNameY = y;

				tableColumnBean.setKey(row.key);
				tableColumnBean.keyX = x + rightspace;
				y = tableColumnBean.columnEnNameY
						+ tableColumnBean.columnEnNameHeight + bottomspace;
				tableColumnBean.keyY = y;

				tableColumnBean.setType(row.type);
				tableColumnBean.typeX = x + rightspace;
				y = tableColumnBean.keyY + tableColumnBean.keyHeight
						+ bottomspace;
				tableColumnBean.typeY = y;

				List<Integer> widths = new ArrayList();
				widths.add(Integer.valueOf(tableColumnBean.columnCnNameWidth));
				widths.add(Integer.valueOf(tableColumnBean.columnEnNameWidth));
				widths.add(Integer.valueOf(tableColumnBean.keyWidth));
				widths.add(Integer.valueOf(tableColumnBean.typeWidth));

				tableColumnBean.x1 = x + maxWidth(widths) + rightspace;
				tableColumnBean.y1 = tableColumnBean.typeY
						+ tableColumnBean.typeHeight + bottomspace;

				tableBean.columns.add(tableColumnBean);
				Collections.sort(tableBean.columns, tableColumnBeanDate);

			}

			if (tableBean.columns != null && tableBean.columns.size() > 0) {// （最后一列）
				TableColumnBean lastColumnBean = tableBean.columns
						.get(tableBean.columns.size() - 1);
				tableBean.x1 = lastColumnBean.x1;
				tableBean.y1 = lastColumnBean.y1;
			} else {
				tableBean.x1 = 0;
				tableBean.y1 = 0;
			}

			tables.add(tableBean);
			Collections.sort(tables, tableBeanDate);
		}

		if (tables != null && tables.size() > 0)
			this.setPreferredSize(new Dimension(
					tables.get(tables.size() - 1).x1 + 20, tables.get(tables
							.size() - 1).y1));
		this.repaint();

	}

	public int maxWidth(List<Integer> widths) {
		int maxone = 0;
		for (Integer temp : widths) {
			if (maxone < (int) temp) {
				maxone = temp;
			}
		}
		return maxone;
	}

	Comparator<TableBean> tableBeanDate = new Comparator<TableBean>() {
		public int compare(TableBean s1, TableBean s2) {
			// 按日期排
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

	Comparator<TableColumnBean> tableColumnBeanDate = new Comparator<TableColumnBean>() {
		public int compare(TableColumnBean s1, TableColumnBean s2) {
			// 按日期排
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (startPoint.x != endPoint.x || startPoint.y != endPoint.y)
			return;

		int c = e.getButton();// 得到按下的鼠标键
		String mouseInfo = null;// 接收信息
		if (c == MouseEvent.BUTTON1)// 判断是鼠标左键按下
		{
			// mouseInfo = "左键";

			Point p = e.getPoint();
			for (TableBean table : tables) {

				if ((p.x >= table.x && p.x <= table.x1)
						&& (p.y >= table.y && p.y <= table.y1)) {
					if (table.selected == true) {
						table.selected = false;
					} else {
						table.selected = true;
					}

				}

				for (TableColumnBean column : table.columns) {
					if ((p.x >= column.x && p.x <= column.x1)
							&& (p.y >= column.y && p.y <= column.y1)) {
						if ("left".equals(column.leftClickSelected)) {
							column.leftClickSelected = null;
						}  else {
							column.leftClickSelected = "left";
						}

					}
				}
			}

			this.repaint();

		} else if (c == MouseEvent.BUTTON3) {// 判断是鼠标右键按下
			// mouseInfo = "右键";

			Point p = e.getPoint();
			for (TableBean table : tables) {

				if ((p.x >= table.x && p.x <= table.x1)
						&& (p.y >= table.y && p.y <= table.y1)) {
					if (table.selected == true) {
						table.selected = false;
					} else {
						table.selected = true;
					}

				}

				for (TableColumnBean column : table.columns) {
					if ((p.x >= column.x && p.x <= column.x1)
							&& (p.y >= column.y && p.y <= column.y1)) {
						 if ("right"
								.equals(column.rightClickSelected)) {
							column.rightClickSelected = null;
						} else {
							column.rightClickSelected = "right";
						}

					}
				}
			}

			this.repaint();
		} else {
			// mouseInfo = "滚轴";
		}

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		// text.append("鼠标进入组件.\n");
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		// text.append("鼠标退出组件.\n");
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// text.append("鼠标按下.\n");
		System.out.println("start" + e.getPoint().x + ":" + e.getPoint().y);
		startPoint = e.getPoint();
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// text.append("鼠标松开.\n");

		endPoint = e.getPoint();

		if (startPoint.x != endPoint.x || startPoint.y != endPoint.y) {// 滑动
			System.out.println("start" + e.getPoint().x + ":" + e.getPoint().y);
			// 起点
			Point p = startPoint;
			for (TableBean table : tables) {

				for (TableColumnBean column : table.columns) {
					if ((p.x >= column.x && p.x <= column.x1)
							&& (p.y >= column.y && p.y <= column.y1)) {
						startColumnBean = column;
						
					}
				}
			}

			// 终点
			p = endPoint;
			for (TableBean table : tables) {

				for (TableColumnBean column : table.columns) {
					if ((p.x >= column.x && p.x <= column.x1)
							&& (p.y >= column.y && p.y <= column.y1)) {
						if (column.relateColumnBeans == null
								|| column.relateColumnBeans.size() == 0) {
							column.relateColumnBeans = new ArrayList();
							column.relateColumnBeans.add(startColumnBean);
							latestRelateColumnBean.add( column);
						} else {

							
							column.relateColumnBeans.add(startColumnBean);
						}

					}
				}
			}
			this.repaint();

		}
	}



	// 鼠标移动事件
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		// 获得鼠标的位置

	}

}
