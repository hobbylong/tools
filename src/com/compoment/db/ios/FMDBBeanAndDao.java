package com.compoment.db.ios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.compoment.db.helper.XmlDBColumnBean;
import com.compoment.db.helper.XmlDBParser;
import com.compoment.db.helper.XmlDBTableBean;

public class FMDBBeanAndDao {

	List<XmlDBTableBean> tables = null;

	public static void main(String[] args) {
		String dbName="User.sqlite";
		FMDBBeanAndDao createrIosDBBeanAndDao=new FMDBBeanAndDao(dbName);
		createrIosDBBeanAndDao.createTableString();
	}

	public FMDBBeanAndDao(String dbName) {

		String classDir = this.getClass().getResource("/").getPath();
		try {

			XmlDBParser xmlDbParser = new XmlDBParser();
			xmlDbParser.parserXml(classDir + "com/compoment/db/db.uxf");
			tables = xmlDbParser.tables;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.DbFileManager(dbName);
		Test();
		
		String m = "\n";
		for (XmlDBTableBean table : tables) {
			
			// Bean .h文件
			m = "\n";
			m += "#import <Foundation/Foundation.h>\n";
			m += "@interface " + table.tableName + "Bean : NSObject\n";
			m += "{\n";
			m += "    int _id;\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "    NSString *" + column.columnName + ";\n";
			}
			m += "}\n";
			m += "@property (nonatomic, assign)int _id;\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "@property (nonatomic, retain) NSString *"
						+ column.columnName + ";\n";
			}
			m += "@end\n";

			System.out.println(m);
			stringToFile("" + table.tableName + "Bean.h", m);

			// Bean .m文件
			m = "\n";
			m += "#import \"" + table.tableName + "Bean.h\"\n";

			m += "@implementation " + table.tableName + "Bean\n";
			m += "@synthesize _id;\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "@synthesize " + column.columnName + ";\n";
			}

			m += "- (void)dealloc\n";
			m += "{\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "    self." + column.columnName + " = nil;\n";
			}
			m += "}\n";

			m += "@end\n";

			System.out.println(m);
			stringToFile("" + table.tableName + "Bean.m", m);

			// Dao.h文件
			m = "";
			m = "\n";
			m += "#import <Foundation/Foundation.h>\n";
			m += "@class FMDatabaseQueue;\n";
			m += "@class " + table.tableName + "Bean;\n";
			m += "@interface " + table.tableName + "Dao : NSObject\n";
			m += "{\n";
			m += "NSString *dbFile;\n";

			m += "FMDatabaseQueue *dbQueue;\n";
			m += "}\n";
			m += "@property (nonatomic , copy) NSString *dbFile;\n";
			m += "@property (nonatomic, retain)FMDatabaseQueue *dbQueue;\n";

			m += "+(" + table.tableName + "Dao *)getInstance;\n";

			m += "- (void)insert" + table.tableName + ":(" + table.tableName
					+ "Bean *)bean;\n";

			m += "- (NSArray *)query" + table.tableName + ";\n";

			for (XmlDBColumnBean column : table.columnsName) {
				m += "- (NSArray *)query" + table.tableName + "By"
						+ column.columnName + ":(NSString *)"
						+ column.columnName + ";\n";
			}

			m += "- (BOOL)delete" + table.tableName + "By" + table.tableName
					+ "Bean:(" + table.tableName + "Bean *)bean;\n";
			m += "@end\n";

			System.out.println(m);
			stringToFile("" + table.tableName + "Dao.h", m);

			// Dao.m文件
			m = "";
			m = "\n";
			m += "#import \"" + table.tableName + "Dao.h\"\n";
			m += "#import \"DbFileManager.h\"\n";
			m += "#import \"FMDatabase.h\"\n";
			m += "#import \"FMDatabaseAdditions.h\"\n";
			m += "#import \"FMDatabasePool.h\"\n";
			m += "#import \"FMDatabaseQueue.h\"\n";
			m += "#import \"" + table.tableName + "Bean.h\"\n";

			m += "static " + table.tableName + "Dao *gSharedInstance = nil;\n";

			m += "@implementation " + table.tableName + "Dao\n";
			m += "@synthesize dbFile;\n";
			m += "@synthesize dbQueue;\n";

			m += "+(" + table.tableName + "Dao *)getInstance\n";
			m += "{\n";
			m += "    @synchronized(self)\n";
			m += "    {\n";
			m += "        if (gSharedInstance == nil)\n";
			m += "			gSharedInstance = [[" + table.tableName
					+ "Dao alloc] init];\n";
			m += "    }\n";
			m += "    return gSharedInstance;	\n";
			m += "}\n";

			m += "- (void)dealloc\n";
			m += "{\n";
			m += "    self.dbFile=nil;\n";
			m += "    self.dbQueue = nil;\n";
			m += "}\n";

			m += "- (id)init\n";
			m += "{\n";
			m += "    \n";
			m += "    self = [super init];\n";
			m += "    if (self)\n";
			m += "    {\n";
			m += "        self.dbFile = [DbFileManager dbFilePath];\n";
			m += "        self.dbQueue = [FMDatabaseQueue databaseQueueWithPath:self.dbFile];\n";
			m += "        \n";
			m += "        \n";
			m += "    }\n";
			m += "    return  self;\n";
			m += "}\n";

			m += "- (" + table.tableName + "Bean *)rsTo" + table.tableName
					+ ":(FMResultSet*)rs\n";
			m += "{\n";
			m += "    " + table.tableName + "Bean *bean = [[" + table.tableName
					+ "Bean alloc] init] ;\n";
			m += "    bean._id = [rs intForColumn:@\"_id\"];\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "    bean." + column.columnName
						+ " = [rs stringForColumn:@\""
						+ column.getSqliteColumnName().toLowerCase() + "\"];\n";
			}
			m += "    return bean;\n";
			m += "}\n";

			
			//insert
			m += "- (void)insert" + table.tableName + ":(" + table.tableName
					+ "Bean *)bean\n";
			m += "{\n";
			m += "    [self.dbQueue inTransaction:^(FMDatabase *db, BOOL *rollback) {\n";
			m += "        [db open];\n";

			String columnString = "";
			String columnString2 = "";
			String columnString3 = "";
			for (XmlDBColumnBean column : table.columnsName) {
				columnString += column.getSqliteColumnName().toLowerCase() + ",";
				columnString2 += "bean." + column.columnName
						+ ",";
				columnString3 += "?" + ",";
			}
			int p = columnString.lastIndexOf(",");
			if (p != -1) {
				columnString = columnString.substring(0, p);

			}

			int p2 = columnString2.lastIndexOf(",");
			if (p2 != -1) {
				columnString2 = columnString2.substring(0, p2);

			}

			int p3 = columnString3.lastIndexOf(",");
			if (p3 != -1) {
				columnString3 = columnString3.substring(0, p3);

			}

			m += "        NSString *sql = @\"insert into "
					+ table.getSqliteTableName() + "(" + columnString
					+ ") values (" + columnString3 + ")\";\n";
			m += "        [db executeUpdate:sql," + columnString2 + "];\n";
			m += "        [db close];\n";
			m += "    }];  \n";
			m += "}\n";

			
			//query
			m += "- (NSArray *)query" + table.tableName + ";\n";
			m += "{\n";
			m += "    __block NSMutableArray *beans = [[NSMutableArray alloc] init] ;  \n";
			m += "    [self.dbQueue inDatabase:^(FMDatabase *db)   {\n";
			m += "        [db open];\n";
			m += "        NSString *sql = @\"select * from "
					+ table.getSqliteTableName() + " \";\n";
			m += "        FMResultSet *rs = [db executeQuery:sql];\n";
			m += "        while ([rs next])\n";
			m += "        {\n";
			m += "            [beans addObject:[self rsTo" + table.tableName
					+ " :rs]]; \n";
			m += "        }\n";
			m += "        [db close];\n";
			m += "    }];\n";
			m += "    return beans;\n";
			m += "}\n";

			for (XmlDBColumnBean column : table.columnsName) {
				m += "- (NSArray *)query" + table.tableName + "By"
						+ column.columnName + ":(NSString *)"
						+ column.columnName + ";\n";
				m += "{\n";
				m += "    __block NSMutableArray *beans = [[NSMutableArray alloc] init] ;  \n";
				m += "    [self.dbQueue inDatabase:^(FMDatabase *db)   {\n";
				m += "        [db open];\n";
				m += "FMResultSet *rs =[db executeQuery:[NSString stringWithFormat:@\"select * from %@ where "
						+ column.getSqliteColumnName().toLowerCase()
						+ " = ?\","
						+ table.getSqliteTableName()
						+ "], [NSString stringWithString:"
						+ column.columnName
						+ "]];";
				m += "        while ([rs next])\n";
				m += "        {\n";
				m += "            [beans addObject:[self rsTo"
						+ table.tableName + " :rs]]; \n";
				m += "        }\n";
				m += "        [db close];\n";
				m += "    }];\n";
				m += "    return beans;\n";
				m += "}\n";
			}

			
			//delete
			m += "- (BOOL)delete" + table.tableName + "By" + table.tableName
					+ "Bean:(" + table.tableName + "Bean *)bean{\n";
			m += "    BOOL success = YES;\n";
			m += "    [self.dbQueue inDatabase:^(FMDatabase *db)   {\n";
			m += "        [db open];\n";
			m += "	[db executeUpdate:\n";
			m += "     [NSString stringWithFormat:@\"delete from %@ where _id = ?\","
					+ table.tableName + "],\n";
			m += "     [NSNumber numberWithInteger:bean._id]];\n";
			m += "	if ([db hadError]) {\n";
			m += "		NSLog(@\"Err %d: %@\", [db lastErrorCode], [db lastErrorMessage]);\n";
			m += "		success = NO;\n";
			m += "}\n";
			m += "        [db close];\n";
			m += "    }];\n";
			m += "	return success;\n";
			m += "    \n";
			m += "}\n";

			m += "@end\n";

			System.out.println(m);

			stringToFile("" + table.tableName + "Dao.m", m);
		}

	}

	public void DbFileManager(String dbName) {
		String m = "\n";
		m += "//针对数据库文件的封装类\n";
		m += "#import <Foundation/Foundation.h>\n";

		m += "@interface DbFileManager : NSObject\n";

		m += "+ (NSString *)documentPath;\n";

		m += "+ (void)checkWithCreateDbFile:(NSString *)fullPath;\n";

		m += "+ (NSString *)dbFilePath;\n";

		m += "+ (BOOL)createFolderInDocment:(NSString *)folderName;\n";

		m += "@end\n";

		System.out.println(m);

		stringToFile("DbFileManager.h", m);

		m = "\n";
		m += "#import \"DbFileManager.h\"\n";
		m += "#define k_DB_NAME @\""+dbName+"\"\n";

		m += "@implementation DbFileManager\n";
		m += "+ (NSString *)documentPath\n";
		m += "{\n";
		m += "    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);\n";
		m += "    return [paths objectAtIndex:0];\n";
		m += "}\n";

		m += "+ (void)checkWithCreateDbFile:(NSString *)fullPath\n";
		m += "{\n";
		m += "    NSFileManager *fileManager = [NSFileManager defaultManager];\n";
		m += "    NSError *error;\n";
		m += "    \n";
		m += "    NSString *dbFileName = k_DB_NAME;\n";
		m += "    BOOL found = [fileManager fileExistsAtPath:fullPath];\n";
		m += "    if(!found)\n";
		m += "    {\n";
		m += "        NSString *resourcePath = [[NSBundle mainBundle] resourcePath];\n";
		m += "        NSString *defaultDBFilePath =\n";
		m += "        [resourcePath stringByAppendingPathComponent:dbFileName];\n";
		m += "        \n";
		m += "        found = [fileManager copyItemAtPath:defaultDBFilePath\n";
		m += "                                     toPath:fullPath\n";
		m += "                                      error:&error];\n";
		m += "        if (!found)\n";
		m += "        {\n";
		m += "            NSAssert1(0,\n";
		m += "                      @\"创建数据库失败 '%@'.\",\n";
		m += "                      [error localizedDescription]);\n";
		m += "        }\n";
		m += "    }\n";
		m += "}\n";

		m += "+ (NSString *)dbFilePath\n";
		m += "{\n";
		m += "    NSString *dbFileName = k_DB_NAME;\n";
		m += "    NSString *documentsDirectory = [DbFileManager documentPath];\n";
		m += "    \n";
		m += "    NSString *dbFilePath =\n";
		m += "    [documentsDirectory stringByAppendingPathComponent:dbFileName];\n";
		m += "    \n";
		m += "    [DbFileManager checkWithCreateDbFile:dbFilePath];\n";
		m += "    \n";
		m += "    return dbFilePath;\n";
		m += "}\n";

		m += "+ (BOOL)createFolderInDocment:(NSString *)folderName\n";
		m += "{\n";
		m += "    NSFileManager *fileManager = [NSFileManager defaultManager];\n";
		m += "    NSString *documentsDirectory = [DbFileManager documentPath];\n";
		m += "    NSString *foldFullPath =  [documentsDirectory stringByAppendingPathComponent:folderName];\n";
		m += "    return [fileManager createDirectoryAtPath:foldFullPath withIntermediateDirectories:YES attributes:nil error:nil];\n";
		m += "}\n";

		m += "@end\n";

		System.out.println(m);

		stringToFile("DbFileManager.m", m);

	}
	
	
	public void Test()
	{
		String classDir = this.getClass().getResource("/").getPath();
		try {

			XmlDBParser xmlDbParser = new XmlDBParser();
			xmlDbParser.parserXml(classDir + "com/compoment/db/db.uxf");
			tables = xmlDbParser.tables;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

		String m = "\n";
		m+="#import <UIKit/UIKit.h>\n";
		
		for (XmlDBTableBean table : tables) {
		m+="#import \""+table.tableName+"Bean.h\"\n";
		m+="#import \""+table.tableName+"Dao.h\"\n";
		}
		
		
		m+="@interface ViewController : UIViewController\n";
		m+="{\n";
		m+="}\n";
		m+="@end\n";

		System.out.println(m);
		stringToFile("ViewController.h", m);

		m="";
		m+="#import \"ViewController.h\"\n";
		m+="@implementation ViewController\n";
		m+="- (void)viewDidLoad\n";
		m+="{\n";
		m+="    [super viewDidLoad];\n";
		for (XmlDBTableBean table : tables) {
		m+="    [NSThread detachNewThreadSelector:@selector(test"+table.tableName+") toTarget:self withObject:nil];\n";
		}
		m+="}\n";

		for (XmlDBTableBean table : tables) {
		m+="- (void)test"+table.tableName+"\n";
		m+="{\n";
		m+="    "+table.tableName+"Dao *dao = ["+table.tableName+"Dao  getInstance];\n";
		m+="    for (int i = 0; i < 12; i++)\n";
		m+="    {\n";
		m+="        @autoreleasepool\n";
		m+="        {\n";
		m+="            "+table.tableName+"Bean *bean = [["+table.tableName+"Bean alloc] init] ;\n";
		
		for (XmlDBColumnBean column : table.columnsName) {
		m+="            bean."+column.columnName+" = [NSString stringWithFormat:@\""+column.columnName+" %d\", i];\n";
		}
		m+="            [dao insert"+table.tableName+":bean];\n";
		m+="            NSLog(@\"hobbytest %@\", @\"dd\");\n";
		m+="        }\n";
		m+="        \n";
		m+="    }\n";
		m+="    \n";
		
		m+="    "+table.tableName+"Dao *dao = ["+table.tableName+"Dao  getInstance];\n";
		m+="    NSArray *beans =   [dao query"+table.tableName+"];\n";
		m+="    NSLog(@\"%@\", beans);\n";
		m+="}\n";
		}

		m+="- (void)viewDidUnload\n";
		m+="{\n";
		m+="    [super viewDidUnload];\n";
		m+="}\n";

		m+="- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation\n";
		m+="{\n";
		m+="    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);\n";
		m+="}\n";

		m+="@end\n";
		
		System.out.println(m);
		stringToFile("ViewController.m", m);
		
	}
	
	public void createTableString()
	{
		String m = "\n";
		
		String classDir = this.getClass().getResource("/").getPath();
		try {

			XmlDBParser xmlDbParser = new XmlDBParser();
			xmlDbParser.parserXml(classDir + "com/compoment/db/db.uxf");
			tables = xmlDbParser.tables;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (XmlDBTableBean table : tables) {
			
			String columnString="";
			for (XmlDBColumnBean column : table.columnsName) {
				columnString+=","+column.getSqliteColumnName().toLowerCase()+" TEXT";
			}
			m+="CREATE TABLE "+table.getSqliteTableName()+" (_id INTEGER PRIMARY KEY AUTOINCREMENT"+columnString+")\n";

		}
	
		
		System.out.println(m);
	}
	

	public void stringToFile(String fileName, String str) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(str);
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
