package com.jkbjhs.postgresdemo;

import com.google.cloud.sql.core.CoreSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Logger;

public class SocketFactory extends javax.net.SocketFactory {

    private static final Logger logger = Logger.getLogger(SocketFactory.class.getName());

    private static final String DEPRECATED_SOCKET_ARG = "SocketFactoryArg";

    private Properties props;

    /**
     * Implements the {@link SocketFactory} constructor, which can be used to create authenticated
     * connections to a Cloud SQL instance.
     */
    public SocketFactory(Properties info) {
        String oldInstanceKey = info.getProperty(DEPRECATED_SOCKET_ARG);
        if (oldInstanceKey != null) {
            logger.warning(
                    String.format(
                            "The '%s' property has been deprecated. Please update your postgres driver and use"
                                    + "the  '%s' property in your JDBC url instead.",
                            DEPRECATED_SOCKET_ARG, CoreSocketFactory.CLOUD_SQL_INSTANCE_PROPERTY));
            info.setProperty(CoreSocketFactory.CLOUD_SQL_INSTANCE_PROPERTY, oldInstanceKey);
        }
        this.props = info;
    }

    @Deprecated
    public SocketFactory(String instanceName) {
        // Deprecated constructor for converting 'SocketFactoryArg' to 'CloudSqlInstance'
        this(createDefaultProperties(instanceName));
    }

    private static Properties createDefaultProperties(String instanceName) {
        Properties info = new Properties();
        info.setProperty(DEPRECATED_SOCKET_ARG, instanceName);
        return info;
    }

    @Override
    public Socket createSocket() throws IOException {
        return CoreSocketFactory.getInstance()
                .connect(props, CoreSocketFactory.POSTGRES_SOCKET_FILE_FORMAT);
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
            throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
            throws IOException {
        throw new UnsupportedOperationException();
    }
}