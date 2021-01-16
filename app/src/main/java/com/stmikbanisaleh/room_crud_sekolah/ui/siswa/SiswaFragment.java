package com.stmikbanisaleh.room_crud_sekolah.ui.siswa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stmikbanisaleh.room_crud_sekolah.AppDatabase;
import com.stmikbanisaleh.room_crud_sekolah.R;
import com.stmikbanisaleh.room_crud_sekolah.Siswa;
import com.stmikbanisaleh.room_crud_sekolah.SiswaAdapter;
import com.stmikbanisaleh.room_crud_sekolah.SiswaDao;

import java.util.List;

public class SiswaFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView = null;
    private List<Siswa> list = null;
    private SiswaAdapter adapter = null;

    private SiswaDao dao = null;
    private View viewDialog, view = null;
    private EditText textNama, textTanggalLahir, textJenisKelamin, textAlamat;
    private Button buttonSave, buttonCancel, buttonDelete;
    private AlertDialog dialog = null;
    private Siswa siswa = null;
//    private Object SiswaViewModel;?

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        SiswaViewModel = ViewModelProviders.of(this).get(SiswaViewModel.class);
        view = inflater.inflate(R.layout.fragment_siswa, container, false);

        setupDialog();
        recyclerView =  view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        dao = AppDatabase.getDb(getContext()).dosenDao();
        list = dao.getAll();
        adapter = new SiswaAdapter(getContext(), list, this);
        recyclerView.setAdapter(adapter);

        ImageButton imageButton =  view.findViewById(R.id.buttonTest);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                buttonAddClick(view);
            }
        });

        return view;
    }


    private void setupDialog() {
        viewDialog = LayoutInflater.from(getContext()).inflate(R.layout.siswa_layout, null);
        textNama = viewDialog.findViewById(R.id.editTextNama);
        textTanggalLahir = viewDialog.findViewById(R.id.editTextTanggalLahir);
        textJenisKelamin = viewDialog.findViewById(R.id.editTextJenisKelamin);
        textAlamat = viewDialog.findViewById(R.id.editTextAlamat);
        buttonSave = viewDialog.findViewById(R.id.buttonSave);
        buttonCancel = viewDialog.findViewById(R.id.buttonCancel);
        buttonDelete = viewDialog.findViewById(R.id.buttonDelete);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Siswa Detail");
        builder.setView(viewDialog);
        dialog = builder.create();
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSave();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDelete();
            }
        });
    }

    private void showDialog() {
        textNama.setText(siswa.getNama());
        textTanggalLahir.setText(siswa.getTanggal_lahir());
        textJenisKelamin.setText(siswa.getJenis_kelamin());
        textAlamat.setText(siswa.getAlamat());
        dialog.show();
    }

    private void processSave() {
        siswa.setNama(textNama.getText().toString());
        siswa.setTanggal_lahir(textTanggalLahir.getText().toString());
        siswa.setJenis_kelamin(textJenisKelamin.getText().toString());
        siswa.setAlamat(textAlamat.getText().toString());
        try {
            if (siswa.getId() > 0) {
                dao.update(siswa);
            } else {
                long id = dao.insert(siswa);
                siswa.setId((int) id);
                list.add(0, siswa);
            }
            adapter.notifyDataSetChanged();
            dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processDelete() {
        try {
            list.remove(siswa);
            dao.delete(siswa);
            adapter.notifyDataSetChanged();
            dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonAddClick(View v) {
        siswa = new Siswa();
        siswa.setId(0);
        buttonDelete.setVisibility(v.INVISIBLE);
        showDialog();
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        siswa = list.get(position);
        buttonDelete.setVisibility(v.VISIBLE);
        showDialog();
    }
}