package test


import com.apploi.Hashids

/**
 * Unit tests for com.apploi.Hashids
 *
 * Created by cmaron on 8/7/14.
 */
class HashidsTest extends GroovyTestCase {

    public void test_one_number() {
        String expected = "NkK9", res
        long num_to_hash = 12345L
        long[] res2
        Hashids a = new Hashids("this is my salt")
        res = a.encode(num_to_hash)
        assert res == expected
        res2 = a.decode(expected)
        assert res2.length == 1
        assert res2[0] == num_to_hash
    }

    public void test_one_two_three() {
        String expected = "laHquq", res
        long[] num_to_hash = [1L, 2L, 3L], res2
        Hashids a = new Hashids("this is my salt")
        res = a.encode(num_to_hash)
        assert res == expected
        res2 = a.decode(expected)
        assert res2.length == 3
        assert res2 == num_to_hash
    }

    public void test_serveral_numbers() {
        String expected = "aBMswoO2UB3Sj", res
        long[] num_to_hash = [683L, 94108L, 123L, 5L], res2
        Hashids a = new Hashids("this is my salt")
        res = a.encode(num_to_hash)
        assert res == expected
        res2 = a.decode(expected)
        assert res2.length == num_to_hash.length
        assert res2 == num_to_hash
    }

    public void test_specifying_custom_hash_length() {
        String expected = "gB0NV05e", res
        long num_to_hash = 1L
        long[] res2
        Hashids a = new Hashids("this is my salt", 8)
        res = a.encode(num_to_hash)
        assert res == expected
        res2 = a.decode(expected)
        assert res2.length == 1
        assert res2[0] == num_to_hash
    }

    public void test_specifying_custom_hash_alphabet() {
        String expected = "2d2b8576", res
        long num_to_hash = 1234567L
        long[] res2
        Hashids a = new Hashids("this is my salt", 0, "0123456789abcdef")
        res = a.encode(num_to_hash)
        assert res == expected
        res2 = a.decode(expected)
        assert res2.length == 1
        assert res2[0] == num_to_hash
    }

    public void test_randomness() {
        String expected = "1Wc8cwcE", res
        long[] num_to_hash = [5L, 5L, 5L, 5L], res2
        Hashids a = new Hashids("this is my salt")
        res = a.encode(num_to_hash)
        assert res == expected
        res2 = a.decode(expected)
        assert res2.length == num_to_hash.length
        assert res2 == num_to_hash
    }

    public void test_randomness_for_incrementing_number_list() {
        String expected = "kRHnurhptKcjIDTWC3sx", res
        long[] num_to_hash = [1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L], res2
        Hashids a = new Hashids("this is my salt")
        res = a.encode(num_to_hash)
        assert res == expected
        res2 = a.decode(expected)
        assert res2.length == num_to_hash.length
        assert res2 == num_to_hash
    }

    public void test_randomness_for_incrementing_numbers() {
        Hashids a = new Hashids("this is my salt")
        assert a.encode(1L) == "NV"
        assert a.decode('NV')[0] == 1L
        assert a.encode(2L) == "6m"
        assert a.decode('6m')[0] == 2L
        assert a.encode(3L) == "yD"
        assert a.decode('yD')[0] == 3L
        assert a.encode(4L) == "2l"
        assert a.decode('2l')[0] == 4L
        assert a.encode(5L) == "rD"
        assert a.decode('rD')[0] == 5L
    }

    public void test_for_values_greater_int_maxval() {
        Hashids a = new Hashids("this is my salt")
        assert a.encode(9876543210123L) == "Y8r7W1kNN"
        assert a.decode('Y8r7W1kNN')[0] == 9876543210123L
    }
}
