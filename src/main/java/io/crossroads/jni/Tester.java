package io.crossroads.jni;

import java.nio.ByteBuffer;

public class Tester {
    public static void main(String [] args) {
        Tester tester = new Tester();
        tester.allocate();
        tester.testLibrary();
        tester.testVersion();
        tester.testConstants();
        tester.testErrors();
        tester.testSocket();
        tester.testOption();
        tester.testPoll();
        tester.testPrintf();
        tester.dispose();
    }

    public Tester() {
    }
    
    private void allocate() {
        System.out.printf("Starting Tester\n");
        xs = new XsLibrary();
        ctx = xs.xs_init();
    }

    private void dispose() {
        xs.xs_term(ctx);
        ctx = 0;
        xs = null;
        System.out.printf("Finished Tester\n");
    }

    private boolean testPrintf() {
        int i = 0;
        Integer j =  33;
        System.out.printf("1: xsLibrary: i=%d, j=%d\n", i, j);
        System.out.println("2: xsLibrary: i=" + i + ", j=" + j);

        return true;
    }

    private boolean testLibrary() {
        if (xs == null) {
            System.out.printf("xsLibrary is null\n");
            return false;
        }
        
        return true;
    }
    
    private void testVersion() {
        System.out.printf("=== VERSION ===\n");
        Integer major = new Integer(0);
        Integer minor = new Integer(0);
        Integer patch = new Integer(0);
        int ver = xs.xs_version(major, minor, patch);
        System.out.printf("XS version is %d = %d.%d.%d\n",
                          ver,
                          major, minor, patch);
        System.out.printf("XS made version is %d\n",
                          xs.xc.makeVersion(7, 8, 9));
    }

    private void testConstants() {
        System.out.printf("=== CONSTANTS ===\n");
        System.out.printf("Constant %20s = %d\n",
                          "XS_VERSION_MAJOR", XsConstants.XS_VERSION_MAJOR);
        System.out.printf("Constant %20s = %d\n",
                          "XS_VERSION_MINOR", XsConstants.XS_VERSION_MINOR);
        System.out.printf("Constant %20s = %d\n",
                          "XS_VERSION_PATCH", XsConstants.XS_VERSION_PATCH);
        System.out.printf("Constant %20s = %d\n",
                          "XS_VERSION", XsConstants.XS_VERSION);
        System.out.printf("Constant %20s = %d\n",
                          "XS_PAIR", XsConstants.XS_PAIR);
        System.out.printf("Constant %20s = %d\n",
                          "XS_PUB", XsConstants.XS_PUB);
        System.out.printf("Constant %20s = %d\n",
                          "XS_SUB", XsConstants.XS_SUB);
        System.out.printf("Constant %20s = %d\n",
                          "XS_REQ", XsConstants.XS_REQ);
        System.out.printf("Constant %20s = %d\n",
                          "XS_REP", XsConstants.XS_REP);
        System.out.printf("Constant %20s = %d\n",
                          "XS_XREQ", XsConstants.XS_XREQ);
        System.out.printf("Constant %20s = %d\n",
                          "XS_XREP", XsConstants.XS_XREP);
        System.out.printf("Constant %20s = %d\n",
                          "XS_PULL", XsConstants.XS_PULL);
        System.out.printf("Constant %20s = %d\n",
                          "XS_PUSH", XsConstants.XS_PUSH);
        System.out.printf("Constant %20s = %d\n",
                          "XS_XPUB", XsConstants.XS_XPUB);
        System.out.printf("Constant %20s = %d\n",
                          "XS_XSUB", XsConstants.XS_XSUB);
        System.out.printf("Constant %20s = %d\n",
                          "XS_SURVEYOR", XsConstants.XS_SURVEYOR);
        System.out.printf("Constant %20s = %d\n",
                          "XS_RESPONDENT", XsConstants.XS_RESPONDENT);
        System.out.printf("Constant %20s = %d\n",
                          "XS_XSURVEYOR", XsConstants.XS_XSURVEYOR);
        System.out.printf("Constant %20s = %d\n",
                          "XS_XRESPONDENT", XsConstants.XS_XRESPONDENT);
        System.out.printf("Constant %20s = %d\n",
                          "XS_ROUTER", XsConstants.XS_ROUTER);
        System.out.printf("Constant %20s = %d\n",
                          "XS_DEALER", XsConstants.XS_DEALER);
        System.out.printf("Constant %20s = %d\n",
                          "XS_MAX_SOCKETS", XsConstants.XS_MAX_SOCKETS);
        System.out.printf("Constant %20s = %d\n",
                          "XS_IO_THREADS", XsConstants.XS_IO_THREADS);
        System.out.printf("Constant %20s = %d\n",
                          "XS_PLUGIN", XsConstants.XS_PLUGIN);
        System.out.printf("Constant %20s = %d\n",
                          "XS_AFFINITY", XsConstants.XS_AFFINITY);
        System.out.printf("Constant %20s = %d\n",
                          "XS_IDENTITY", XsConstants.XS_IDENTITY);
        System.out.printf("Constant %20s = %d\n",
                          "XS_SUBSCRIBE", XsConstants.XS_SUBSCRIBE);
        System.out.printf("Constant %20s = %d\n",
                          "XS_UNSUBSCRIBE", XsConstants.XS_UNSUBSCRIBE);
        System.out.printf("Constant %20s = %d\n",
                          "XS_RATE", XsConstants.XS_RATE);
        System.out.printf("Constant %20s = %d\n",
                          "XS_RECOVERY_IVL", XsConstants.XS_RECOVERY_IVL);
        System.out.printf("Constant %20s = %d\n",
                          "XS_SNDBUF", XsConstants.XS_SNDBUF);
        System.out.printf("Constant %20s = %d\n",
                          "XS_RCVBUF", XsConstants.XS_RCVBUF);
        System.out.printf("Constant %20s = %d\n",
                          "XS_RCVMORE", XsConstants.XS_RCVMORE);
        System.out.printf("Constant %20s = %d\n",
                          "XS_FD", XsConstants.XS_FD);
        System.out.printf("Constant %20s = %d\n",
                          "XS_EVENTS", XsConstants.XS_EVENTS);
        System.out.printf("Constant %20s = %d\n",
                          "XS_TYPE", XsConstants.XS_TYPE);
        System.out.printf("Constant %20s = %d\n",
                          "XS_LINGER", XsConstants.XS_LINGER);
        System.out.printf("Constant %20s = %d\n",
                          "XS_RECONNECT_IVL", XsConstants.XS_RECONNECT_IVL);
        System.out.printf("Constant %20s = %d\n",
                          "XS_BACKLOG", XsConstants.XS_BACKLOG);
        System.out.printf("Constant %20s = %d\n",
                          "XS_RECONNECT_IVL_MAX", XsConstants.XS_RECONNECT_IVL_MAX);
        System.out.printf("Constant %20s = %d\n",
                          "XS_MAXMSGSIZE", XsConstants.XS_MAXMSGSIZE);
        System.out.printf("Constant %20s = %d\n",
                          "XS_SNDHWM", XsConstants.XS_SNDHWM);
        System.out.printf("Constant %20s = %d\n",
                          "XS_RCVHWM", XsConstants.XS_RCVHWM);
        System.out.printf("Constant %20s = %d\n",
                          "XS_MULTICAST_HOPS", XsConstants.XS_MULTICAST_HOPS);
        System.out.printf("Constant %20s = %d\n",
                          "XS_RCVTIMEO", XsConstants.XS_RCVTIMEO);
        System.out.printf("Constant %20s = %d\n",
                          "XS_SNDTIMEO", XsConstants.XS_SNDTIMEO);
        System.out.printf("Constant %20s = %d\n",
                          "XS_IPV4ONLY", XsConstants.XS_IPV4ONLY);
        System.out.printf("Constant %20s = %d\n",
                          "XS_KEEPALIVE", XsConstants.XS_KEEPALIVE);
        System.out.printf("Constant %20s = %d\n",
                          "XS_SURVEY_TIMEOUT", XsConstants.XS_SURVEY_TIMEOUT);
        System.out.printf("Constant %20s = %d\n",
                          "XS_MORE", XsConstants.XS_MORE);
        System.out.printf("Constant %20s = %d\n",
                          "XS_DONTWAIT", XsConstants.XS_DONTWAIT);
        System.out.printf("Constant %20s = %d\n",
                          "XS_SNDMORE", XsConstants.XS_SNDMORE);
        System.out.printf("Constant %20s = %d\n",
                          "XS_POLLIN", XsConstants.XS_POLLIN);
        System.out.printf("Constant %20s = %d\n",
                          "XS_POLLOUT", XsConstants.XS_POLLOUT);
        System.out.printf("Constant %20s = %d\n",
                          "XS_POLLERR", XsConstants.XS_POLLERR);
    }

    private void testErrors() {
        System.out.printf("=== ERRORS ===\n");
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ENOMEM", XsErrors.ENOMEM, xs.xs_strerror(XsErrors.ENOMEM));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EFAULT", XsErrors.EFAULT, xs.xs_strerror(XsErrors.EFAULT));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EINVAL", XsErrors.EINVAL, xs.xs_strerror(XsErrors.EINVAL));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EMFILE", XsErrors.EMFILE, xs.xs_strerror(XsErrors.EMFILE));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EINTR", XsErrors.EINTR, xs.xs_strerror(XsErrors.EINTR));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ENAMETOOLONG", XsErrors.ENAMETOOLONG, xs.xs_strerror(XsErrors.ENAMETOOLONG));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ENODEV", XsErrors.ENODEV, xs.xs_strerror(XsErrors.ENODEV));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EAGAIN", XsErrors.EAGAIN, xs.xs_strerror(XsErrors.EAGAIN));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ETIMEDOUT", XsErrors.ETIMEDOUT, xs.xs_strerror(XsErrors.ETIMEDOUT));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ENOTSUP", XsErrors.ENOTSUP, xs.xs_strerror(XsErrors.ENOTSUP));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EPROTONOSUPPORT", XsErrors.EPROTONOSUPPORT, xs.xs_strerror(XsErrors.EPROTONOSUPPORT));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ENOBUFS", XsErrors.ENOBUFS, xs.xs_strerror(XsErrors.ENOBUFS));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ENETDOWN", XsErrors.ENETDOWN, xs.xs_strerror(XsErrors.ENETDOWN));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EADDRINUSE", XsErrors.EADDRINUSE, xs.xs_strerror(XsErrors.EADDRINUSE));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EADDRNOTAVAIL", XsErrors.EADDRNOTAVAIL, xs.xs_strerror(XsErrors.EADDRNOTAVAIL));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ECONNREFUSED", XsErrors.ECONNREFUSED, xs.xs_strerror(XsErrors.ECONNREFUSED));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EINPROGRESS", XsErrors.EINPROGRESS, xs.xs_strerror(XsErrors.EINPROGRESS));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ENOTSOCK", XsErrors.ENOTSOCK, xs.xs_strerror(XsErrors.ENOTSOCK));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EAFNOSUPPORT", XsErrors.EAFNOSUPPORT, xs.xs_strerror(XsErrors.EAFNOSUPPORT));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "EFSM", XsErrors.EFSM, xs.xs_strerror(XsErrors.EFSM));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ENOCOMPATPROTO", XsErrors.ENOCOMPATPROTO, xs.xs_strerror(XsErrors.ENOCOMPATPROTO));
        System.out.printf("Error %20s = %9d - [%s]\n",
                          "ETERM", XsErrors.ETERM, xs.xs_strerror(XsErrors.ETERM));
    }

    private void testSocket() {
        System.out.printf("=== SOCKET ===\n");
        int ret;
        long sock = xs.xs_socket(ctx, XsConstants.XS_REQ);
        System.out.printf("XS REQ socket created\n");
        int id = xs.xs_bind(sock, "tcp://127.0.0.1:6666");
        System.out.printf("XS REQ socket bound: %d\n", id);
        ret = xs.xs_shutdown(sock, id);
        System.out.printf("XS REQ socket shut down: %d\n", ret);
        ret = xs.xs_close(sock);
        System.out.printf("XS REQ socket closed: %d\n", ret);
    }

    private void testOption() {
        System.out.printf("=== OPTION ===\n");
        int ret;
        Integer iget;
        Long lget;
        ByteBuffer bget = ByteBuffer.allocateDirect(1024);
        int iset;
        long lset;
        ByteBuffer bset = ByteBuffer.allocateDirect(1024);
        String sid = "This is a TEST";
        String snew = "";
        
        long sock = xs.xs_socket(ctx, XsConstants.XS_REQ);
        XsOption option = new XsOption();
        option.setSocket(sock);

        iget = 0;
        ret = option.getSockoptInt(XsConstants.XS_TYPE, iget);
        System.out.println("XS get socket type (" +
                           XsConstants.XS_TYPE +
                           "): " +
                           iget + 
                           " [" +
                           XsConstants.XS_REQ + 
                           "] (" +
                           ret +
                           ")");

        lget = 0L;
        ret = option.getSockoptLong(XsConstants.XS_MAXMSGSIZE, lget);
        System.out.println("XS get socket max msg size (" +
                           XsConstants.XS_MAXMSGSIZE +
                           "): " +
                           lget + 
                           " (" +
                           ret +
                           ")");

        snew = "";
        ret = option.getSockoptBuffer(XsConstants.XS_IDENTITY, bget);
        if (bget.limit() > 0) {
            byte[] bnew = new byte[bget.limit()];
            bget.get(bnew);
            snew = new String(bnew);
        }
        System.out.println("XS get socket identity (" +
                           XsConstants.XS_IDENTITY +
                           "): " +
                           bget.limit() +
                           ":[" +
                           snew +
                           "] (" +
                           ret +
                           ")");

        iget = 0;
        ret = option.getSockoptInt(XsConstants.XS_IPV4ONLY, iget);
        System.out.println("XS get socket IPv4 only (" +
                           XsConstants.XS_IPV4ONLY +
                           "): " +
                           iget + 
                           " (" +
                           ret +
                           ")");

        iset = 1 - iget;
        ret = option.setSockoptInt(XsConstants.XS_IPV4ONLY, iset);
        System.out.println("XS set socket IPv4 only to " +
                           iset + 
                           " (" +
                           ret +
                           ")");

        iget = 0;
        ret = option.getSockoptInt(XsConstants.XS_IPV4ONLY, iget);
        System.out.println("XS get socket IPv4 only (" +
                           XsConstants.XS_IPV4ONLY +
                           "): " +
                           iget + 
                           " (" +
                           ret +
                           ")");

        lget = 0L;
        ret = option.getSockoptLong(XsConstants.XS_MAXMSGSIZE, lget);
        System.out.println("XS get socket max msg size (" +
                           XsConstants.XS_MAXMSGSIZE +
                           "): " +
                           lget + 
                           " (" +
                           ret +
                           ")");

        lset = 1011L;
        ret = option.setSockoptLong(XsConstants.XS_MAXMSGSIZE, lset);
        System.out.println("XS set socket max msg size to " +
                           lset + 
                           " (" +
                           ret +
                           ")");

        lget = 0L;
        ret = option.getSockoptLong(XsConstants.XS_MAXMSGSIZE, lget);
        System.out.println("XS get socket max msg size (" +
                           XsConstants.XS_MAXMSGSIZE +
                           "): " +
                           lget + 
                           " (" +
                           ret +
                           ")");
        
        snew = "";
        ret = option.getSockoptBuffer(XsConstants.XS_IDENTITY, bget);
        if (bget.limit() > 0) {
            byte[] bnew = new byte[bget.limit()];
            bget.get(bnew);
            snew = new String(bnew);
        }
        System.out.println("XS get socket identity (" +
                           XsConstants.XS_IDENTITY +
                           "): " +
                           bget.limit() +
                           ":[" +
                           snew +
                           "] (" +
                           ret +
                           ")");

        bset.clear();
        bset.put(sid.getBytes());
        bset.flip();
        ret = option.setSockoptBuffer(XsConstants.XS_IDENTITY, bset);
        System.out.println("XS set socket identity to [" +
                           sid +
                           "] (" +
                           ret +
                           ")");

        ret = option.getSockoptBuffer(XsConstants.XS_IDENTITY, bget);
        snew = "";
        if (bget.limit() > 0) {
            byte[] bnew = new byte[bget.limit()];
            bget.get(bnew);
            snew = new String(bnew);
        }
        System.out.println("XS get socket identity (" +
                           XsConstants.XS_IDENTITY +
                           "): " +
                           bget.limit() +
                           ":[" +
                           snew +
                           "] (" +
                           ret +
                           ")");

        ret = xs.xs_close(sock);
        System.out.printf("XS REQ socket closed: %d\n", ret);
    }

    private void testPoll() {
        System.out.printf("=== POLL ===\n");

        long sock1 = xs.xs_socket(ctx, XsConstants.XS_REP);
        long sock2 = xs.xs_socket(ctx, XsConstants.XS_SUB);
        long sock3 = xs.xs_socket(ctx, XsConstants.XS_PULL);
        int ret;
        int j;

        xs.xs_bind(sock1, "tcp://127.0.0.1:6666");
        xs.xs_bind(sock2, "tcp://127.0.0.1:6667");
        xs.xs_bind(sock3, "tcp://127.0.0.1:6668");

        XsPoller poller = new XsPoller();
        poller.addSocket(sock1, XsConstants.XS_POLLIN);
        poller.addSocket(sock2, XsConstants.XS_POLLIN | XsConstants.XS_POLLOUT);

        ret = poller.poll(0);
        System.out.printf("XS sockets polled 1: %d\n", ret);
        for (j = 0; j < poller.getNext(); ++j)
            System.out.printf("XS   item[%2d]: sock = %8x | einp = %4d | eout = %4d\n",
                              j, poller.getSocket(j),
                              poller.getInpEvent(j), poller.getOutEvent(j));

        ret = poller.poll(0);
        System.out.printf("XS sockets polled 2: %d\n", ret);
        for (j = 0; j < poller.getNext(); ++j)
            System.out.printf("XS   item[%2d]: sock = %8x | einp = %4d | eout = %4d\n",
                              j, poller.getSocket(j),
                              poller.getInpEvent(j), poller.getOutEvent(j));

        poller.addSocket(sock3, XsConstants.XS_POLLOUT);
        ret = poller.poll(0);
        System.out.printf("XS sockets polled 3: %d\n", ret);
        for (j = 0; j < poller.getNext(); ++j)
            System.out.printf("XS   item[%2d]: sock = %8x | einp = %4d | eout = %4d\n",
                              j, poller.getSocket(j),
                              poller.getInpEvent(j), poller.getOutEvent(j));

        ret = poller.poll(0);
        System.out.printf("XS sockets polled 4: %d\n", ret);
        for (j = 0; j < poller.getNext(); ++j)
            System.out.printf("XS   item[%2d]: sock = %8x | einp = %4d | eout = %4d\n",
                              j, poller.getSocket(j),
                              poller.getInpEvent(j), poller.getOutEvent(j));
        
        poller.reset();
        poller.addSocket(sock3, XsConstants.XS_POLLOUT);
        ret = poller.poll(0);
        System.out.printf("XS sockets polled 5: %d\n", ret);
        for (j = 0; j < poller.getNext(); ++j)
            System.out.printf("XS   item[%2d]: sock = %8x | einp = %4d | eout = %4d\n",
                              j, poller.getSocket(j),
                              poller.getInpEvent(j), poller.getOutEvent(j));

        poller = null;
        System.gc();

        xs.xs_close(sock3);
        xs.xs_close(sock2);
        xs.xs_close(sock1);
    }

    private XsLibrary xs = null;
    long ctx = 0;
}
