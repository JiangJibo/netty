/*
 * Copyright 2016 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.channel;

import java.nio.channels.Selector;

import io.netty.util.IntSupplier;

/**
 * Default select strategy.
 *
 * 默认选择策略实现类
 */
final class DefaultSelectStrategy implements SelectStrategy {

    /**
     * 单例
     */
    static final SelectStrategy INSTANCE = new DefaultSelectStrategy();

    private DefaultSelectStrategy() { }

    /**
     * 如果当前有任务未执行结束,则返回如果通过{@link Selector#selectNow()},也就是不会再继续Select
     * 如果任务，则执行select
     *
     * @param selectSupplier The supplier with the result of a select result.
     * @param hasTasks       true if tasks are waiting to be processed.
     * @return
     * @throws Exception
     */
    @Override
    public int calculateStrategy(IntSupplier selectSupplier, boolean hasTasks) throws Exception {
        return hasTasks ? selectSupplier.get() : SelectStrategy.SELECT;
    }

}