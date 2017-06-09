#include <stdio.h>

static int (*AddByCallback)(int a,int b)=NULL;

void RegisterAdd(int (*callback)(int,int))
{
    AddByCallback=callback;
}

void DoAddByCallback(int a,int b)
{
    int res;
    if(AddByCallback==NULL)
    {
        printf("error: please register the Add function first!\n");
        return;
    }
    res=AddByCallback(a,b);
    printf("Java execute add: %d+%d=%d\n",a,b,res);
}