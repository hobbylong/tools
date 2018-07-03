package com.compoment.db.tabledocinterfacedoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

import com.compoment.cut.CompomentBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;

public class DBTablesPanel extends JPanel implements MouseListener {

	public Graphics2D g2;
	public List<TableBean> tables;

	public DBTablesPanel() {
		tables = new ArrayList();
		this.addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics pGraphics) {
		super.paintComponent(pGraphics);
		Graphics2D g2 = (Graphics2D) pGraphics;
		// g2.scale(aZoom, aZoom);
		Rectangle2D bounds = getBounds();

		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {
				// 背景色
				if (table.selected) {
					g2.setColor(Color.red);

				} else {
					g2.setColor(Color.black);
				}
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

				g2.setFont(new Font("宋体", Font.PLAIN, 12)); // 改变字体大小
				g2.setColor(Color.black);
				g2.drawString(column.columnCnName, column.columnCnNameX,
						column.columnCnNameY);
				g2.drawString(column.columnEnName, column.columnEnNameX,
						column.columnEnNameY);
				g2.drawString(column.type, column.typeX, column.typeY);
				g2.drawString(column.key, column.keyX, column.keyY);

			}

			
			if (table.isMainTable) {
				g2.setColor(Color.green);

			} else {
				g2.setColor(Color.black);
			}
			g2.setFont(new Font("宋体", Font.PLAIN, 12)); // 改变字体大小
			g2.drawString(table.tableCnName + "(" + table.tableEnName + ""
					+ table.id + ")", table.tableCnNameX, table.tableCnNameY);

			if (table.selected) {
				g2.setColor(Color.red);

			} else {
				g2.setColor(Color.black);
			}

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

		}

	}

	
	 public Dimension getPreferredSize() {
		 Dimension theSize = new Dimension(3000, 3000);
         return theSize;
 }
	public void cleanSelectTables() {

		for (TableBean table : tables) {
			table.selected = false;
		}
		this.repaint();
	}

	public void setDBTables(List<InterfaceBean> interfaceBeans) {
		tables = new ArrayList();

		int x = 0;
		int y = 0;
		int everyTableYModify = 20;

		int leftspace = 3;
		int rightspace = 3;
		int topspace = 3;
		int bottomspace = 3;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			// 数据表
			TableBean tableBean = new TableBean();

			tableBean.tableCnName = interfaceBean.title;// 表中文名
			tableBean.tableEnName = interfaceBean.enName;// 表英文名
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

			List<Group> groups = interfaceBean.respondGroups;
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
					int rowCount = 0;

					Collections.sort(group.rows, rowDate);
					for (Row row : group.rows) {
						// 列

						if (tableBean.columns != null
								&& tableBean.columns.size() > 0) {// 已经有首列（非首列）
							TableColumnBean firstColumnBean = tableBean.columns
									.get(0);
							y = firstColumnBean.y;
							x = tableBean.columns
									.get(tableBean.columns.size() - 1).x1;
						} else {// 首列
							y = y;
							x = x;
						}

						TableColumnBean tableColumnBean = new TableColumnBean();

						tableColumnBean.y = y;
						tableColumnBean.x = x;

						tableColumnBean.setColumnCnName(row.cnName);
						tableColumnBean.columnCnNameX = x + rightspace;
						tableColumnBean.columnCnNameY = y
								+ tableColumnBean.columnCnNameHeight
								+ bottomspace;

						tableColumnBean.setColumnEnName(row.enName);
						tableColumnBean.columnEnNameX = x + rightspace;
						y = tableColumnBean.columnCnNameY
								+ tableColumnBean.columnCnNameHeight
								+ bottomspace;
						tableColumnBean.columnEnNameY = y;

						tableColumnBean.setKey(row.remarks);
						tableColumnBean.keyX = x + rightspace;
						y = tableColumnBean.columnEnNameY
								+ tableColumnBean.columnEnNameHeight
								+ bottomspace;
						tableColumnBean.keyY = y;

						tableColumnBean.setType(row.type);
						tableColumnBean.typeX = x + rightspace;
						y = tableColumnBean.keyY + tableColumnBean.keyHeight
								+ bottomspace;
						tableColumnBean.typeY = y;

						List<Integer> widths = new ArrayList();
						widths.add(Integer
								.valueOf(tableColumnBean.columnCnNameWidth));
						widths.add(Integer
								.valueOf(tableColumnBean.columnEnNameWidth));
						widths.add(Integer.valueOf(tableColumnBean.keyWidth));
						widths.add(Integer.valueOf(tableColumnBean.typeWidth));

						tableColumnBean.x1 = x + maxWidth(widths) + rightspace;
						tableColumnBean.y1 = tableColumnBean.typeY
								+ tableColumnBean.typeHeight + bottomspace;

						tableBean.columns.add(tableColumnBean);
						Collections
								.sort(tableBean.columns, tableColumnBeanDate);

						rowCount++;
					}
				}
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

	Comparator<Row> rowDate = new Comparator<Row>() {
		public int compare(Row s1, Row s2) {
			// 按日期排
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

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
						table.isMainTable=false;
					} else {
						table.selected = true;
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
					if(table.selected)
					{
					if (table.isMainTable == true) {
						table.isMainTable = false;
					} else {
						table.isMainTable = true;
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
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// text.append("鼠标松开.\n");
	}
}
