#include <stdbool.h>
#include <stdio.h>

#include "doubleLList st.h"
typedef int ElementType;

struct DNode
{
    int data;
    struct DNode* next;
    struct DNode* prev;
};

struct DListStruct
{
    struct DNode* head;
    struct DNode* tail;
    size_t size;
};

typedef struct DNode *DPosition;
typedef struct DListStruct *DList;

DList CreateDList( void ){
    DList L;
    L = malloc(sizeof(L));
    L->head = NULL;
    L->tail = NULL;
    L->size = 0;
    return L;
}

void MakeEmptyDList( DList L){
    struct DNode* temp;
    while (L->head != NULL)
    {
        temp = L->head;
        L->head = L->head->next;
        free(temp);
    }
}

int SizeDList(DList L){
    int size = 0;
    while (L->head != NULL){
        size++;
        L->head = L->head->next;
    }
    return size;
}

DPosition DHeader(DList L){
    return L->head;
}

DPosition DFooter(DList L){
    return L->tail;
}

int IsEmptyDList(DList L){
    if (DHeader(L) == NULL)
    {
        return 1;
    }
    else
    {
        return 0;
    }
}

void InsertDList(ElementType X, DPosition P){
    struct DNode* temp, *tp;
    temp = malloc(sizeof(struct DNode));
    temp->data = X;
    temp->prev = NULL;
    temp->next = NULL;
    tp = P;
    while (tp->next != NULL)
    {
        tp = tp->next;
    }
    tp->next = temp;
    temp->prev = tp;
    return temp;
}

void InsertDListIth(ElementType X, int i, DList L){
    struct DNode* newP = NULL;
    struct DNode* temp = DHeader(L);
    struct DNode* temp2 = NULL;
    int pos = 1;
    
    while (pos != i)
    {
        temp = temp->next;
        pos++;
    }
    temp2 = temp->next;
    temp->next = newP;
    temp2->prev = newP;
    newP->prev = temp2;
    newP->next = temp;
    return newP;
}

void addDList(ElementType X, DList L){

}

DPosition FindDList(ElementType e){
    DPosition P, ct;
    P = DHeader(P);
    while (e != P->data)
    {
        P = P->next;
    }
    return P;
}

void DeleteElement(ElementType e, DList L){
    DPosition Del, P;
    Del = FindDList(e);
    P = DHeader(L);

    if(P->next == Del){
        P->next = Del->next;
        free(Del);
    }
    else if (Del->next != NULL && Del->prev != NULL) {
        Del->prev->next = Del->next;
        Del->next->prev = Del->prev;
        free(Del);
    } else if (DFooter(L) == Del) {
        Del->prev->next = NULL;
        DFooter(L)->Prev = Del->prev;
        free(Del);
    }  
}

ElementType RemoveElementAt(int i, DList L){
    struct DNode* temp = DHeader(L);
    struct DNode* temp2 = NULL;
    int position = 1;
    if (i == position)
    {
        temp2 = temp->next;
        temp2->prev = temp;
        free(temp);
    }
    
    while (i != position)
    {
        temp = temp->next;
        position++;
    }
    temp2 = temp->prev;
    temp2->next = temp->prev;
    temp->next->prev = temp2;
    free(temp);
    return;
}

DPosition Advance(DPosition P){
    return P->next;
}

DPosition Back(DPosition P){
    return P->prev;
}

ElementType Retrieve(DPosition P){
    return P->data;
}

void PrintDList(char *name, DList L){
    struct DNode *node;
    while (node != L->tail)
    {
        node = L->head->data;
        printf("%d\n",node->data);
        node = L->head->next;
    }
    return;
}