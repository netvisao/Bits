unsigned int reverseBits(unsigned int num)
{
    unsigned int BITS = sizeof(num) * 8;
    unsigned int reverse_num = 0, i;
    
    for (int i = 0; i < NO_OF_BITS; i++)
      reverse_num |= ((2^i & num) << (BITS - 1 - i))
    return reverse_num;
}

int main()
{
    unsigned int x = 2; 
    printf("%u", reverseBits(x));
    getchar();
}
