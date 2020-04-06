package com.songzuedu.discovery;

import java.util.List;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public interface LoadBalanceStrategy {

    String selectHost(List<String> repos);

}
