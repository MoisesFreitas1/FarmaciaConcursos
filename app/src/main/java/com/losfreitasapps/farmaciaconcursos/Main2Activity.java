package com.losfreitasapps.farmaciaconcursos;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager FM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FM = getFragmentManager();
        FM.beginTransaction().replace(R.id.content_principal, new Bem_Vindo()).commit();

        ImageView iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_share.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.losfreitasapps.farmaciaconcursos");
                share.setType("text/plain");
                startActivity(share);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_farm10) {
            FM.beginTransaction().replace(R.id.content_principal, new farm10()).commit();
        } else if (id == R.id.nav_farm20) {
            FM.beginTransaction().replace(R.id.content_principal, new farm20()).commit();
        } else if (id == R.id.nav_farm30) {
            FM.beginTransaction().replace(R.id.content_principal, new farm30()).commit();
        }else if (id == R.id.nav_l3820) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_faf1201108714f4d877a5ad2e5910903.pdf?dn=L3820.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l5991) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_6a4d8adba9774ac1894b91238991056c.pdf?dn=L5991.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l6360) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_6603f6e9a26d4412a522ae28a4d06aad.pdf?dn=L6360.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l8080) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_062ef63660d946d39aae2bb18f2c46a5.pdf?dn=L8080.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l9279) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_2a5b4eddb59845fc9d9b2af4792a6ad2.pdf?dn=L9279.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l9787) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_41623a0749d3400f974f5498324234cd.pdf?dn=L9787.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l9965) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_4033348a44144c8a82a3517f64d79705.pdf?dn=L9965.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l10213) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_41e64cd760bc477a9f5668e5838eba48.pdf?dn=L10213.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l10699) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_df1d58a23784450cb474002936cc73ef.pdf?dn=L10699.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l11343) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_81858fb6822240f48eedb843baffeba4.pdf?dn=L11343.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_l13021) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_3ad6f8f5ce534651b820a1d2da89aeb0.pdf?dn=L13021.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p6) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_42d96a8ecf744aeab4c6a0a186555adb.pdf?dn=P6.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p132) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_8d555967f6934e7ebdefb6f8ca2d0aec.pdf?dn=P132.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p176) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_970d381f55de4c70b5c2fbb56640a527.pdf?dn=P176.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p312) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_4b3bbd8085024b5dab64f3b742ec0694.pdf?dn=P312.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p344) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_9c8bc0df89d5489d958ebf791ccb94ec.pdf?dn=P344.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p371) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_da97ef8f93f542329b30c3b413fcac83.pdf?dn=P371_gm.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p500) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_a182f9ee76954f58889989a9188f2d21.pdf?dn=P500.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p698) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_7eaf6e0fe4bd4876922754d2518c4c49.pdf?dn=P698.pdf\n");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p761) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_ab5965f81f104159acef576055f056a4.pdf?dn=P761_gm.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p783) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_32e0b9cb1450493d931130360e4c2fbb.pdf?dn=P783.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p786) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_e85433974a6a4065844f7a0e36e5facb.pdf?dn=P786_gm.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p801) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_8bcbb0c4635a4f408c43ff43161f4931.pdf?dn=P801.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p802) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_bb1f8011f2c44fb6b9214d7b38c7ee7c.pdf?dn=P802.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p1017) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_cb2f337a87084dc697aa36259e77d7df.pdf?dn=P1017.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p1051) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_ffd06e5807c5464f9bc6f2091b601b1b.pdf?dn=P1051.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p1565) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_a6767eeb7bc14deabcb8a8349f693655.pdf?dn=P1565-ms_gm.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_p3916) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_2df3222595554870bc160bde834a5c63.pdf?dn=P3916_gm.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r17) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_6673e892698444c896d26cf330bbb382.pdf?dn=R17.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r18) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_d909bde2a74f4ef586be3ff687806131.pdf?dn=R18.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r33) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_240f4afac96c4fcc9c55fce74ec40c1e.pdf?dn=R33_19abril.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r3303) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_dfef6a93cdbc437ab5257a2cb339b99a.pdf?dn=R33.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r44) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_ccae75c8455e4eb0bd4f8e99ee613a59.pdf?dn=RDC442009.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r102) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_94465b5b5eee4a67a9648af9d1823d7a.pdf?dn=R102.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r135) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_9319557a29b9480898e388b83a122554.pdf?dn=R135.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r137) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_68b0f8864c214d8db866cd354b80075a.pdf?dn=R137.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r140) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_56b3dac7456a40f187c4b246b9856339.pdf?dn=R140.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r173) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_b8705def152d445a813d4ca86b6b1095.pdf?dn=R173.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r199) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_a4b8400c08904075ae2a1d43e75c4f9d.pdf?dn=R199.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r210) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_8fca159aad144dc1976532639726ef40.pdf?dn=R210.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r214) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_8c350644ec034d09af7fc42cedbed086.pdf?dn=R214.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r221) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_2c07946e00484f9ea7e5241009be9895.pdf?dn=R221.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r268) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_6540533086024315a191906c8b715f1a.pdf?dn=R268.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r328) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_ee8581d63ea84bea88b14281b533ad0d.pdf?dn=R328.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_r338) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_2cb2c28df5c84572b65fbf49d463f5d2.pdf?dn=R338.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_et) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_506c3e40c90942bda6319a425f59db93.pdf?dn=CodigoDeEtica.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_d3181) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_7e1dcc6227ce41e7b7455748f34e1b7b.pdf?dn=D3181.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_d3675) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_7e23d69fa9db43f29f069f6ffabd0f4c.pdf?dn=D3675.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_d4074) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_f1b8e4b300784bd7ab192cff432bb6ab.pdf?dn=D4074.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_d4202) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_a32f629ed9f04a6093c2bb2836bb4de8.pdf?dn=D4204.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_d5090) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_6b8a19c7575a4c7bacfe6cbb2423b9ab.pdf?dn=D5090.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_d85878) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_a27aa3df81f64e749fc618ac286fd8aa.pdf?dn=D85878.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_in9) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_bfb69aa46b4948608211c9406d14a8f6.pdf?dn=IN909.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_in10) {
            Uri uri = Uri.parse("https://static.wixstatic.com/ugd/f0d361_fd2ae7762ff940049b268599a0539dbe.pdf?dn=IN1009.pdf");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_email) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "technologiesmx@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contato [FARMÁCIA CONCURSOS]");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Olá,\n");
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
        }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }
