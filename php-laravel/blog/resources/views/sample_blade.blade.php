<!DOCTYPE html>
<html>
    <body>

        <h1>This is a sample view</h1>

        <p title="Sample blade view">
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
culpa qui officia deserunt mollit anim id est laborum.
        </p>

        <ul>
            <li>{{ $var1 }}</li>
            <li>{{ $var2 }}</li>
            <li>{{ $var3 }}</li><!-- php code still works -->
        </ul>
        @if($var1 == 'Hamburger')
            I love hamburgers.
        @endif

        <ul>
            @foreach($orders as $order)
                <li>{{ $order->name }}</li>
            @endforeach
        </ul>
    </body>
</html>
