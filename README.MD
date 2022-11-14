# Introduce

和hela一起用，点一下就可以添加字段，妈妈再也不用担心select的字段太多了!

# 使用说明

首先需要先在设置里面->工具->DbClicker 里面，设置好数据库连接信息。其中url必须改成jdbc，不支持r2dbc。  
然后重启ide，你就可以在gradle标签旁边看到DbClickInfo标签，点击就可以看到数据表列表了。  
双击字段（表名是没用的），信息就会加在文件中，如`T_USER.ID,`。select多个字段的时候，一直点点点就好了，so easy!  
如果数据库有更新，需要手动点击 refresh 按钮刷新数据。