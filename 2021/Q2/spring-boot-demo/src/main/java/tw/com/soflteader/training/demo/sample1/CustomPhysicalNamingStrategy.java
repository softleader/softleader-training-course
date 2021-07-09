package tw.com.soflteader.training.demo.sample1;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import java.util.Locale;

public class CustomPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {
    protected String tablePrefix() {
        return "sl_";
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        Identifier identifier = super.toPhysicalTableName(name, jdbcEnvironment);
        // table要加上前綴
        return new Identifier(tablePrefix() + identifier.getText(), identifier.isQuoted());
    }

    @Override
    protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
        // 去掉Entity關鍵字
        String identifier = name.toLowerCase(Locale.ROOT).replace("_entity", "");
        return new Identifier(identifier, quoted);
    }
}
