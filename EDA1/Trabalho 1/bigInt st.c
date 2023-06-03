#include <stdbool.h>
#include <stdio.h>

#include <doubleLList st.h>

typedef struct DList BigInt;

BigInt big_new(char *num){

}

BigInt sum_b(BigInt a, BigInt b){
    int c = 0, s;
    while(a->head != NULL && b->head != NULL){
        if (a->head != NULL && b1->head != NULL) {
            s = (a->head->data) + (b->head->data) % 10;
            c = (a->head->data) + (b->head->data) / 10;
            a->head = a->head->next;
            b->head = b->head->next;
        }
        else if (a->head == NULL && b1->head != NULL) {
            s = ((b1->head->data) + c) % 10;
            c = ((b1->head->data) + c) / 10;
            b1->head = b1->head->next;
        }
        else if (a->head != NULL && b1->head == NULL) {
            s = ((a->head->data) + c) % 10;
            c = ((a->head->data) + c) / 10;
            a->head = a->head->next;
        }
    }
    if (c != 0){

    }
}

BigInt sub_b(BigInt a, BigInt b){
    
    while (a->head != NULL || b1->head != NULL) {
        if (a->head != NULL && b1->head != NULL) {
            if ((a->head->data) + c >= (b1->head->data)) {
                s = ((a->head->data) + c - (b1->head->data));
                c = 0;
            }
            else {
                s = ((a->head->data) + c + 10 - (b1->head->data));
                c = -1;
            }
            a->head = a->head->next;
            b1->head = b1->head->next;
        }
        else if (a->head != NULL && b1->head == NULL) {
            if (a->head->data >= 1) {
                s = ((a->head->data) + c);
                c = 0;
            }
            else {
                if (c != 0) {
                    s = ((a->head->data) + 10 + c);
                    c = -1;
                }
                else
                    s = a->head->data;
            }
            a->head = a->head->next;
}

BigInt mult_b(BigInt a, BigInt b){

}

BigInt div_b(BigInt a, BigInt b){

}

BigInt mod_b(BigInt a, BigInt b){

}

void print_b(BigInt a){

}