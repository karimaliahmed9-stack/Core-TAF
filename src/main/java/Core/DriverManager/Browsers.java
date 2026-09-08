package Core.DriverManager;

public enum Browsers {
    EDGE{
        @Override
        public AbstractDriver GetDriverFactory() {
            return new EdgeFactory();
        }
    },
    CHROME {
        @Override
        public AbstractDriver GetDriverFactory() {
            return new ChromeFactory();
        }
    };

    public abstract AbstractDriver GetDriverFactory();
}
