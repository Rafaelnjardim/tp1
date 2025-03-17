#include <stdio.h>
#include <string.h>
#include <stdlib.h>
    void Junta(char teste[],char teste2[],char Resp[]){
        int i=0;
        while (teste[i]!='\n'&& teste2[i]!='\n')
        {
            if (teste[i]!='\n')
            {
                Resp[i]=teste[i];
            }
            if (teste2[i]!='\n')
            {
                Resp[i++]=teste[i];
            }
            i+=2;
        }
        if (teste[i]!='\n')
        {
            while (teste[i]!='\n')
            {
               Resp[i]=teste[i];
            }
            
        }
        if (teste2[i]!='\n')
        {
            while (teste2[i]!='\n')
            {
               Resp[i]=teste2[i];
            }
            
        }
        


    }



int main(){
    char teste[100];
    char teste2[100];
    char Resp[100];
        fgets(teste, sizeof(teste),stdin);
        teste[strcspn(teste,"\n")]='\0';
        fgets(teste2, sizeof(teste2),stdin);
        teste[strcspn(teste2,"\n")]='\0';
        Junta(teste,teste2,Resp);
        printf("%s",Resp);


}










