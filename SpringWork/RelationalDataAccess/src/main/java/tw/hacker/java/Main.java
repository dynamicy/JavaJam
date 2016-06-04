package tw.hacker.java;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main
{
    public static void main(String args[])
    {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:mem");
        dataSource.setPassword("");

        //  Input the data souce provided above
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Create table of DB
        jdbcTemplate.execute("drop table customers if exists");
        jdbcTemplate.execute("create table customers(" + "id serial, first_name varchar(255), last_name varchar(255))");

        //  Insert data
        String names[] = "John Woo;Jeff Dean;Mary Jean;Steve Cho".split(";");
        for (String fullname : names)
        {
            String name[] = fullname.split(" ");
            System.out.printf("Input data: %s %s\n", name[0], name[1]);
            jdbcTemplate.update("INSERT INTO customers(first_name, last_name) values(?,?)", name[0], name[1]);
        }

        // Query data
        List<Customer> results = jdbcTemplate.query("select * from customers where first_name = ?", new Object[] {"John"}, new RowMapper<Customer>()
        {
            public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException
            {
                return new Customer(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"));
            }});

            for(Customer customer : results)
            {
                System.out.println(customer);
            }
    }
}
