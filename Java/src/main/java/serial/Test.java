package serial;

public class Test {


    public static void main(String[] args) {
        String content = "\n[mysqld]\nport\u003d{{infra_mysql_port}}\ndatadir\u003d{{infra_mysql_datadir}}\nsocket\u003d{{infra_mysql_socket}}\nuser\u003d{{infra_mysql_user}}\n# Disabling symbolic-links is recommended to prevent assorted security risks\nsymbolic-links\u003d0\nbind-address \u003d 0.0.0.0\n\nserver-id\u003d{{this_mysql_server_id}}\nauto_increment_increment\u003d{{auto_increment_step}}\nauto_increment_offset\u003d{{this_mysql_server_id}}\ncharacter-set-server \u003d utf8\n\nmax_connect_errors \u003d 10240\nmax_connections \u003d 102400\n\n# Replication Master Server (default)\n# binary logging is required for replication\nlog-bin\u003dmysql-bin\n\n# binary logging format - mixed recommended\nbinlog_format\u003dmixed\n\nslow_query_log \u003d 1\nlong_query_time \u003d {{infra_mysql_long_query_time}}\nslow-query-log-file \u003d {{infra_mysql_slow_query_log_file}}\nsql_mode\u003d\"STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION\"\nvalidate_password\u003doff\n\nmax_allowed_packet \u003d 50M\nsync_binlog \u003d 1\nbinlog_cache_size \u003d 4M\nmax_binlog_size \u003d 1G\nexpire_logs_days \u003d 7\n\n[mysqld_safe]\nlog-error\u003d{{infra_mysql_log_error}}\npid-file\u003d{{infra_mysql_pid}}";
        content = content.replace("[mysqld_safe]", "");
        System.out.println(content);
    }
}
