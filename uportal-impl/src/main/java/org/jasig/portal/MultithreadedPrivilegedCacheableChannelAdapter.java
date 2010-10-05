/**
 * Copyright (c) 2000-2009, Jasig, Inc.
 * See license distributed with this file and available online at
 * https://www.ja-sig.org/svn/jasig-parent/tags/rel-10/license-header.txt
 */
package org.jasig.portal;

/**
 * Internal adapter for a multithreaded channel that is also both privileged and cacheable.
 * @author Peter Kharchenko {@link <a href="mailto:pkharchenko@interactivebusiness.com">pkharchenko@interactivebusiness.com</a>}
 * @version $Revision$
 * @see MultithreadedChannelAdapter
 */

public class MultithreadedPrivilegedCacheableChannelAdapter extends MultithreadedCacheableChannelAdapter implements IPrivilegedChannel {
    public MultithreadedPrivilegedCacheableChannelAdapter(IMultithreadedChannel channel, String uid) {
        super(channel,uid);
    }

    public void setPortalControlStructures(PortalControlStructures pcs) throws PortalException {
        ((IMultithreadedPrivileged)channel).setPortalControlStructures(pcs, uid);
    }
}