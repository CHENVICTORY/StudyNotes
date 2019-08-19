# android 当中的ActivityManager  Activitymanager是一个与系统所有的Activityy进行交互的类
* 我们可以用起来获得程序的内存信息,进程信息,还可以用来终止某一个进程(用户进程),

* 先看实例:
            <code> 

                  protected void onCreat(){

                  ActivityManager activityManager=getSystemService(Context.ACTIVITY_MANAGER); //这样就得到的系统的

                  MemoryInfo memInfo = new MemoryInfo();//存放内存信息的对象  
                  activityManager.getMemoryInfo(memInfo);//传入参数，将获得数据保存在memInfo对象中  
                 long availMem = memInfo.availMem/1000000;//可用内存  
                  boolean isLowMem = memInfo.lowMemory;//是否达到最低内存  
                long threshold = memInfo.threshold/1000000;//临界值，达到这个值，进程就要被杀死  
                long totalMem = memInfo.totalMem/1000000;//总内存  
                 Log.i(TAG, "avail:" + availMem + ",isLowMem:" + isLowMem + ",threshold:" + threshold + ",totalMem:" + totalMem);  
  
                    activityManager.clearApplicationUserData()//清楚当前应用的用户数据 
                

                 List<RunningAppProcessInfo> list1 = activityManager.getRunningAppProcesses();  
                for(RunningAppProcessInfo info :list1){  
                 int importance = info.importance;//通过给进程标值来给进程分类，越重要的进程，那么数值就越低  
               Log.i(TAG, "importance: "+importance);  
               int importanceReasonCode = info.importanceReasonCode;//重要性的原因，也是通过数值去判断。  
               Log.i(TAG, "importanceReasonCode: "+importanceReasonCode);  
               ComponentName impoartanceReasonComponent = info.importanceReasonComponent;//重要性原因的组件，返回一个ComponentName的对象。  
               Log.i(TAG, "impoartanceReasonComponent: "+impoartanceReasonComponent);  
              int importanceReasonPid = info.importanceReasonPid;//其他客户端进程的进程id号，如果没有其他进程返回0  
             Log.i(TAG, "importanceReasonPid : "+importanceReasonPid);  
               int lastTrimLevel = info.lastTrimLevel;//提供给ComponentCallbacks2.onTrimMemory(int)方法的参数。上一次提交给进程的内饰水平  
              Log.i(TAG, "lastTrimLevel: "+lastTrimLevel);  
              int lru = info.lru;//在一个特殊的进程中，提供更精细密度的衡量值，目前只维护IMPORTANCE_BACKGROUND  
              Log.i(TAG, "lru ："+lru);  
              int pid = info.pid;//进程id  
             Log.i(TAG, "pid ："+pid);  
               String proName = info.processName;//进程名  
             Log.i(TAG, "proName : " + proName);  
            int uid = info.uid;//用户id  
             Log.i(TAG, "uid : " + uid);  
            Log.i(TAG, "================================");  }    

                 List<ProcessErrorStateInfo> list2 = activityManager.getProcessesInErrorState();  
    if (list2 != null) {  
    for (ProcessErrorStateInfo info : list2) {  
    int condition = info.condition;// 进程进入的条件  
    Log.i(TAG, "condition: " + condition);  
    byte[] data = info.crashData;// crash数据  
    if (data != null) {  
    Log.i(TAG, "data: " + data.toString());  
    } else {  
    Log.i(TAG, "data: null");  
    }  
    String longMsg = info.longMsg;// 对条件condition的描述  
    Log.i(TAG, "longMsg: " + longMsg);  
    int pid = info.pid;// 进程id  
    Log.i(TAG, "pid: " + pid);  
    String proName = info.processName;// 进程名  
    Log.i(TAG, "proName: " + proName);  
    String shortMsg = info.shortMsg;  
    Log.i(TAG, "shortMsg: " + shortMsg);  
    String sTrace = info.stackTrace;// 堆栈追踪到的信息  
    Log.i(TAG, "sTrace: " + sTrace);  
    String tag = info.tag;// activity名是否与错误有关联  
    Log.i(TAG, "tag: " + tag);  
    int uid = info.uid;  
    Log.i(TAG, "uid: " + uid);  
    int describeContents = info.describeContents();// 数据包裹的描述  
    Log.i(TAG, "describeContents: " + describeContents);  
        }  
    }  


        // 添加权限
               // <uses-permission android:name="android.permission.GET_TASKS"/>  
               List<RunningTaskInfo> list3 = activityManager.getRunningTasks(10);  
            if (list3 != null) {  
                for (RunningTaskInfo info : list3) {  
                    ComponentName baseActivity = info.baseActivity;//任务主activity名  
                    Log.i(TAG, "baseActivity: " + baseActivity);  
                    CharSequence description = info.description;//任务的描述  
                    if(description!=null){  
                        Log.i(TAG, "description: " + description.toString());  
                    }else{  
                        Log.i(TAG, "description: null" );  
                    }  
                    int id = info.id;//任务的id号  
                    Log.i(TAG, "id: " + id);  
                    int numActivity = info.numActivities;//该任务的activity的数量  
                    Log.i(TAG, "numActivity: " + numActivity);  
                    int numRunning = info.numRunning;//当前活动的activity数量  
                    Log.i(TAG, "numRunning: " + numRunning);  
                    Bitmap bitmap = info.thumbnail;//缩略图  
                    Log.i(TAG, "bitmap: " + bitmap);  
                    ComponentName topActivity = info.topActivity;//当前活动activity中处于最顶端的activity  
                    Log.i(TAG, "topActivity: " + topActivity);  
                    int content = info.describeContents();//描述文本  
                    Log.i(TAG, "content: " + content);  
                    Log.i(TAG, "================================");  
                }  
            }      
                 }