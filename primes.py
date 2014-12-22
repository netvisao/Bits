def insert_sorted(t, sl, ll):
    if len(ll) == 1:
        if t == ll[0]:
            return

        if t > ll[0]:
            sl.insert(sl.index(ll[0]) + 1, t)
        else:
            sl.insert(sl.index(ll[0]), t)
        return

    idx = len(ll) / 2
    if t > sl[idx]:
        insert_sorted(t, sl, ll[idx:])
    else:
        insert_sorted(t, sl, ll[:idx])


def is_prime(t, ll):

    if t in ll:
        return True

    if t < ll[0]:
        return False

    p0 = ll[0]
    for p in ll:
        if t % p == 0:
            return False

        if not p > pow(t, .5):
            p0 = p
        else:
            break

    for c in range(p0, t / 2):
        if t % c == 0:
            return False

    ll.append(t)
    return True

if __name__ == "__main__":
    nl = int(raw_input())

    inputs = []
    primes = [2]

    for l in range(nl):
        line = raw_input()
        b0, b1 = map(lambda x: int(x), line.split(' '))
        inputs.append((b0, b1))

    b00, b01 = b0, b1

    for (b0, b1) in inputs:

        if b00 > b0:
            b00 = b0
        if b01 < b1:
            b01 = b1

    for i in range(b00, b01):
        is_prime(i, primes)

    for i in range(b0, b1 + 1):
        if is_prime(i, primes):
            print i
    print