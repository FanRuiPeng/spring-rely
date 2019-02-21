package com.bmf.test;

import org.junit.Test;

import java.lang.management.*;
import java.util.List;
import java.util.Set;

public class MemoryTest {

    @Test
    public void memoryTest() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println(memoryMXBean.getHeapMemoryUsage().toString());
        System.out.println(memoryMXBean.getNonHeapMemoryUsage().toString());
        System.out.println(memoryMXBean.getObjectPendingFinalizationCount());
        System.out.println(memoryMXBean.getObjectName().toString());
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        System.out.println(classLoadingMXBean.getLoadedClassCount());
        System.out.println(classLoadingMXBean.getTotalLoadedClassCount());
        System.out.println(classLoadingMXBean.getUnloadedClassCount());
        System.out.println(classLoadingMXBean.getObjectName());
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        System.out.println(compilationMXBean.getName());
        System.out.println(compilationMXBean.getTotalCompilationTime());
        System.out.println(compilationMXBean.isCompilationTimeMonitoringSupported());
        System.out.println(compilationMXBean.getObjectName());
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        garbageCollectorMXBeans.forEach(m -> {
            System.out.println(m.getCollectionCount());
            System.out.println(m.getCollectionTime());
            System.out.println(m.getName());
            System.out.println(m.getMemoryPoolNames());
            System.out.println(m.getObjectName());
            System.out.println(m.isValid());
        });
        List<MemoryManagerMXBean> memoryManagerMXBeans = ManagementFactory.getMemoryManagerMXBeans();
        memoryManagerMXBeans.forEach(m -> {
            System.out.println(m.getName());
            System.out.println(m.getMemoryPoolNames());
            System.out.println(m.getObjectName());
            System.out.println(m.isValid());
        });
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        memoryPoolMXBeans.forEach(m -> {
            System.out.println(m.getCollectionUsage());
            System.out.println(m.getCollectionUsageThreshold());
            System.out.println(m.getCollectionUsageThresholdCount());
            System.out.println(m.getMemoryManagerNames());
            System.out.println(m.getName());
            System.out.println(m.getPeakUsage());
            System.out.println(m.getType());
            System.out.println(m.getUsage());
            System.out.println(m.getUsageThreshold());
            System.out.println(m.getUsageThresholdCount());
            System.out.println(m.getObjectName());
            System.out.println(m.isCollectionUsageThresholdExceeded());
            System.out.println(m.isCollectionUsageThresholdSupported());
            System.out.println(m.isUsageThresholdExceeded());
            System.out.println(m.isUsageThresholdSupported());
            System.out.println(m.isValid());
        });
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(operatingSystemMXBean.getArch());
        System.out.println(operatingSystemMXBean.getAvailableProcessors());
        System.out.println(operatingSystemMXBean.getName());
        System.out.println(operatingSystemMXBean.getSystemLoadAverage());
        System.out.println(operatingSystemMXBean.getVersion());
        System.out.println(operatingSystemMXBean.getObjectName());
        Set<Class<? extends PlatformManagedObject>> platformManagementInterfaces = ManagementFactory.getPlatformManagementInterfaces();
        platformManagementInterfaces.forEach(m -> {
            System.out.println(m.getName());
        });
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getBootClassPath());
        System.out.println(runtimeMXBean.getClassPath());
        runtimeMXBean.getInputArguments().forEach(System.out::println);
        System.out.println(runtimeMXBean.getLibraryPath());
        System.out.println(runtimeMXBean.getManagementSpecVersion());
        System.out.println(runtimeMXBean.getSpecName());
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println(threadMXBean.getCurrentThreadCpuTime());
        System.out.println(threadMXBean.getCurrentThreadUserTime());
        System.out.println(threadMXBean.getDaemonThreadCount());
        System.out.println(threadMXBean.getObjectName());
    }
}
