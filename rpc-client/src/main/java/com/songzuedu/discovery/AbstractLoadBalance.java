package com.songzuedu.discovery;

import java.util.List;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public abstract class AbstractLoadBalance implements LoadBalanceStrategy {

    @Override
    public String selectHost(List<String> repos) {
        //repos可能为空， 可能只有一个
        if (repos == null || repos.size() == 0) {
            return null;
        }
        if (repos.size() == 1) {
            return repos.get(0);
        }

        return doSelect(repos);
    }

    protected abstract String doSelect(List<String> repos);
}
