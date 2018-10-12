# -*- coding: utf-8 -*-
import tensorflow as tf


W = tf.Variable([2.], dtype=tf.float32, name='W')
b = tf.Variable([1.], dtype=tf.float32, name='b')

x = tf.placeholder(tf.float32, name='x')

with tf.name_scope('linear_model'):
    linear_model = W * x + b

y = tf.placeholder(tf.float32, name='y')

with tf.name_scope('loss'):
    loss = tf.reduce_sum(tf.square(linear_model - y))
    tf.summary.scalar('loss', loss)

optimizer = tf.train.GradientDescentOptimizer(0.001)
train = optimizer.minimize(loss)

x_train = [1, 2, 3, 4, 5]
y_train = [2.9, 5.9, 8.9, 11.9, 14.9]

# sess = tf.Session()
with tf.Session() as sess:
    merged = tf.summary.merge_all()
    writer = tf.summary.FileWriter('./tfboard', sess.graph)

    sess.run(tf.global_variables_initializer())

    for i in range(10000):
        summary, _ = sess.run([merged, train], {x: x_train, y: y_train})
        writer.add_summary(summary, i)

        if i % 100 == 0:
            print('count: %s, W: %s, b: %s, loss: %s' %
                  (i, sess.run(W), sess.run(b), sess.run(loss, {x: x_train, y: y_train})))
