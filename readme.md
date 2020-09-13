# First Setup
***
### Requirements
* java 8
* Mysql
---
### First step
##### Download the repository
1. Create a folder to put the project
2. Clone the project, using git or download directly from github
```git
git clone https://github.com/fadedphoenix7/Dotomotica_3.0.git
```

##### Download MySql
1. Go to [Mysql Community downloads](https://dev.mysql.com/downloads/) and download Mysql for your SO
2. Just installed

---
### Build a local Database
In you terminal go to ..\MySQL\MySQL Server 8.0\bin
and enter ``` mysql -u USERNAME -p``` (first time use root)
Go to the project folder and looking \database_file\database_schema.sql
On the terminal type:
```
source FILE_PATH/database_schema.sql
```
this create a local database called "domotica". to verificate
```
show databases;
```

you will see 
```mysql
+--------------------+
| Database           |
+--------------------+
| domotica_test      |
| information_schema |
| mysql              |
| performance_schema |
| sakila             |
| sys                |
| world              |
+--------------------+
```

also check if the tables were imported
```
use domotica;
show tables;
```

you should see 
```
+--------------------+
| Tables_in_domotica |
+--------------------+
| area               |
| areatoarea         |
| areatodevice       |
| areatouser         |
| device             |
| devicetouser       |
| house              |
| housecode          |
```
and now we have a local database to start to use the project.
***
### Connect the project with the database
Open the project in the IDE
open the file ```Domotica\src\resources\META-INF\persistence.xml```
look for
```xml
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://DATABASE_LOCATION/domotica?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC"/>
      <property name="javax.persistence.jdbc.user" value=USERNAME/>
      <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
      <property name="javax.persistence.jdbc.password" value=PASSWORD/>
      <property name="hibernate.event.merge.entity_copy_observer" value="allow"/>
```
* ```DATABASE_LOCATION``` = direction for your DB (in this case localhost).
* ```USERNAME``` = user from your mysql (can be root).
* ```PASSWORD``` = password from mysql.

Save the changes and now we can start to use the project.

---

